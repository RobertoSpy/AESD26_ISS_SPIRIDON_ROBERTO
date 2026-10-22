package com.example.demo.library.aspect;

import org.aspectj.lang.annotation.Pointcut;


final class BorrowingPointcuts {

    private BorrowingPointcuts() {
    }

    @Pointcut("execution(* com.example.demo.library.service.BorrowingService.borrowBook(..))")
    static void borrow() {
    }

    @Pointcut("execution(* com.example.demo.library.service.BorrowingService.returnBook(..))")
    static void giveBack() {
    }

    @Pointcut("borrow() || giveBack()")
    static void borrowingOperation() {
    }
}
