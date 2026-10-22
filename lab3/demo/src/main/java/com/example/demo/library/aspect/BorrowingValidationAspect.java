package com.example.demo.library.aspect;

import com.example.demo.library.exception.BookNotAvailableException;
import com.example.demo.library.exception.BookNotFoundException;
import com.example.demo.library.exception.BorrowingLimitExceededException;
import com.example.demo.library.exception.MemberNotFoundException;
import com.example.demo.library.model.Book;
import com.example.demo.library.model.Member;
import com.example.demo.library.policy.BorrowingPolicy;
import com.example.demo.library.repository.BookRepository;
import com.example.demo.library.repository.MemberRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Order(3)
public class BorrowingValidationAspect {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final BorrowingPolicy borrowingPolicy;

    public BorrowingValidationAspect(MemberRepository memberRepository,
                                     BookRepository bookRepository,
                                     BorrowingPolicy borrowingPolicy) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.borrowingPolicy = borrowingPolicy;
    }

    @Before("com.example.demo.library.aspect.BorrowingPointcuts.borrow() && args(memberId, bookId)")
    public void validate(String memberId, String bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        if (!book.isAvailable()) {
            throw new BookNotAvailableException(book.getTitle());
        }
        if (!borrowingPolicy.canBorrow(member)) {
            throw new BorrowingLimitExceededException(member.getName(), borrowingPolicy.getMaxAllowedBooks(member));
        }
    }
}
