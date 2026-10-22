package com.example.demo.library.service;

import com.example.demo.library.config.LibraryProperties;
import com.example.demo.library.event.HighBorrowingVolumeEvent;
import com.example.demo.library.exception.BookNotBorrowedException;
import com.example.demo.library.exception.BookNotFoundException;
import com.example.demo.library.exception.BorrowingLimitExceededException;
import com.example.demo.library.exception.BookNotAvailableException;
import com.example.demo.library.exception.MemberNotFoundException;
import com.example.demo.library.model.Book;
import com.example.demo.library.model.Member;
import com.example.demo.library.policy.BorrowingPolicy;
import com.example.demo.library.repository.BookRepository;
import com.example.demo.library.repository.MemberRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class DefaultBorrowingService implements BorrowingService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final BorrowingPolicy borrowingPolicy;
    private final ApplicationEventPublisher eventPublisher;
    private final LibraryProperties properties;

    public DefaultBorrowingService(MemberRepository memberRepository,
                                   BookRepository bookRepository,
                                   BorrowingPolicy borrowingPolicy,
                                   ApplicationEventPublisher eventPublisher,
                                   LibraryProperties properties) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.borrowingPolicy = borrowingPolicy;
        this.eventPublisher = eventPublisher;
        this.properties = properties;
    }

    @Override
    public synchronized void borrowBook(String memberId, String bookId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));


        if (!book.isAvailable()) {
            throw new BookNotAvailableException(book.getTitle());
        }
        if (!borrowingPolicy.canBorrow(member)) {
            throw new BorrowingLimitExceededException(member.getName(), borrowingPolicy.getMaxAllowedBooks(member));
        }

        book.markBorrowed();
        member.addBorrowedBook(book.getId());

        if (member.getBorrowedCount() >= properties.highVolumeThreshold()) {
            eventPublisher.publishEvent(
                    new HighBorrowingVolumeEvent(member.getId(), member.getName(), member.getBorrowedCount()));
        }
    }

    @Override
    public synchronized void returnBook(String memberId, String bookId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        if (!member.hasBorrowed(bookId)) {
            throw new BookNotBorrowedException(member.getName(), book.getTitle());
        }

        member.removeBorrowedBook(bookId);
        book.markReturned();
    }
}
