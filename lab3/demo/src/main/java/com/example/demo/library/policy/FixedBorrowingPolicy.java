package com.example.demo.library.policy;

import com.example.demo.library.model.Member;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnProperty(name = "library.borrowing-policy", havingValue = "fixed", matchIfMissing = true)
public class FixedBorrowingPolicy implements BorrowingPolicy {

    static final int FIXED_LIMIT = 3;

    @Override
    public int getMaxAllowedBooks(Member member) {
        return FIXED_LIMIT;
    }
}
