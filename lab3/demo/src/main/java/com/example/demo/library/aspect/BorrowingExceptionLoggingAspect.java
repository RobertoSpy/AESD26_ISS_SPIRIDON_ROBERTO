package com.example.demo.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Order(1)
public class BorrowingExceptionLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(BorrowingExceptionLoggingAspect.class);

    @AfterThrowing(pointcut = "com.example.demo.library.aspect.BorrowingPointcuts.borrowingOperation()", throwing = "ex")
    public void logFailure(JoinPoint joinPoint, Exception ex) {
        log.error("[ERROR] Operatia '{}' a esuat ({}): {}",
                joinPoint.getSignature().getName(), ex.getClass().getSimpleName(), ex.getMessage());
    }
}
