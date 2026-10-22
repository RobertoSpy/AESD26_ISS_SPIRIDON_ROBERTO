package com.example.demo.library.aspect;

import com.example.demo.library.model.Book;
import com.example.demo.library.model.Member;
import com.example.demo.library.repository.BookRepository;
import com.example.demo.library.repository.MemberRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Order(2)
public class BorrowingAuditAspect {

    private static final Logger log = LoggerFactory.getLogger(BorrowingAuditAspect.class);

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public BorrowingAuditAspect(MemberRepository memberRepository, BookRepository bookRepository) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    @Around("com.example.demo.library.aspect.BorrowingPointcuts.borrowingOperation() && args(memberId, bookId)")
    public Object audit(ProceedingJoinPoint joinPoint, String memberId, String bookId) throws Throwable {
        String memberName = memberRepository.findById(memberId).map(Member::getName).orElse("necunoscut");
        String bookTitle = bookRepository.findById(bookId).map(Book::getTitle).orElse("necunoscuta");

        long start = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long elapsedMs = (System.nanoTime() - start) / 1_000_000;
            log.info("[AUDIT] metoda={} | membru='{}' | carte='{}' | timp={} ms",
                    joinPoint.getSignature().getName(), memberName, bookTitle, elapsedMs);
        }
    }
}
