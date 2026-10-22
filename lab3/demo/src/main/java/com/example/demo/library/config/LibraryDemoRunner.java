package com.example.demo.library.config;

import com.example.demo.library.exception.LibraryException;
import com.example.demo.library.model.Book;
import com.example.demo.library.model.Member;
import com.example.demo.library.model.MemberRole;
import com.example.demo.library.repository.BookRepository;
import com.example.demo.library.repository.MemberRepository;
import com.example.demo.library.service.BorrowingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(2)
public class LibraryDemoRunner implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final BorrowingService borrowingService;

    public LibraryDemoRunner(MemberRepository memberRepository,
                             BookRepository bookRepository,
                             BorrowingService borrowingService) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.borrowingService = borrowingService;
    }

    @Override
    public void run(String... args) {
        memberRepository.save(new Member("m1", "Ana", MemberRole.STUDENT));
        memberRepository.save(new Member("m2", "Bogdan", MemberRole.PREMIUM));
        bookRepository.save(new Book("b1", "Clean Code", "Robert C. Martin"));
        bookRepository.save(new Book("b2", "Effective Java", "Joshua Bloch"));
        bookRepository.save(new Book("b3", "Spring in Action", "Craig Walls"));
        bookRepository.save(new Book("b4", "Java Concurrency in Practice", "Brian Goetz"));

        attempt(() -> borrowingService.borrowBook("m1", "b1"));
        attempt(() -> borrowingService.borrowBook("m1", "b2"));
        attempt(() -> borrowingService.borrowBook("m1", "b3"));
        attempt(() -> borrowingService.borrowBook("m2", "b1"));
        attempt(() -> borrowingService.borrowBook("zzz", "b4"));
        attempt(() -> borrowingService.borrowBook("m2", "b2"));
        attempt(() -> borrowingService.borrowBook("m2", "b3"));
        attempt(() -> borrowingService.borrowBook("m2", "b4"));
        attempt(() -> borrowingService.returnBook("m1", "b1"));
        attempt(() -> borrowingService.returnBook("m1", "b1"));
    }

    private void attempt(Runnable operation) {
        try {
            operation.run();
        } catch (LibraryException ignored) {

        }
    }
}
