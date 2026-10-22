package com.example.demo.library;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.demo.library.exception.BookNotAvailableException;
import com.example.demo.library.exception.BookNotBorrowedException;
import com.example.demo.library.exception.BorrowingLimitExceededException;
import com.example.demo.library.exception.MemberNotFoundException;
import com.example.demo.library.model.Book;
import com.example.demo.library.model.Member;
import com.example.demo.library.model.MemberRole;
import com.example.demo.library.policy.BorrowingPolicy;
import com.example.demo.library.policy.RoleBasedBorrowingPolicy;
import com.example.demo.library.repository.BookRepository;
import com.example.demo.library.repository.MemberRepository;
import com.example.demo.library.service.BorrowingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"library.borrowing-policy=role-based", "library.high-volume-threshold=2"})
class BorrowingServiceTest {

    @Autowired BorrowingService service;
    @Autowired BorrowingPolicy policy;
    @Autowired MemberRepository members;
    @Autowired BookRepository books;

    @BeforeEach
    void seed() {
        members.save(new Member("s", "Student", MemberRole.STUDENT));
        members.save(new Member("p", "Premium", MemberRole.PREMIUM));
        for (int i = 1; i <= 5; i++) {
            books.save(new Book("b" + i, "Book " + i, "Author"));
        }
    }

    @Test
    void policyIsSelectedFromConfiguration() {
        assertThat(policy).isInstanceOf(RoleBasedBorrowingPolicy.class);
    }

    @Test
    void studentLimitIsEnforcedByValidationAspect() {
        service.borrowBook("s", "b1");
        service.borrowBook("s", "b2");
        assertThatThrownBy(() -> service.borrowBook("s", "b3")).isInstanceOf(BorrowingLimitExceededException.class);
    }

    @Test
    void premiumHasHigherLimit() {
        for (int i = 1; i <= 5; i++) {
            service.borrowBook("p", "b" + i);
        }
        assertThat(members.findById("p").orElseThrow().getBorrowedCount()).isEqualTo(5);
    }

    @Test
    void unknownMemberAndUnavailableBookAreRejected() {
        assertThatThrownBy(() -> service.borrowBook("nope", "b1")).isInstanceOf(MemberNotFoundException.class);
        service.borrowBook("p", "b1");
        assertThatThrownBy(() -> service.borrowBook("s", "b1")).isInstanceOf(BookNotAvailableException.class);
    }

    @Test
    void returningFreesTheBook() {
        service.borrowBook("s", "b1");
        service.returnBook("s", "b1");
        assertThat(books.findById("b1").orElseThrow().isAvailable()).isTrue();
        assertThatThrownBy(() -> service.returnBook("s", "b1")).isInstanceOf(BookNotBorrowedException.class);
    }
}
