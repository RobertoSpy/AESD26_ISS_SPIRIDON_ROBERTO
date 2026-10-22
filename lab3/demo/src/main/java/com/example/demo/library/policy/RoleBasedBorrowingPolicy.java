package com.example.demo.library.policy;

import com.example.demo.library.model.Member;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnProperty(name = "library.borrowing-policy", havingValue = "role-based")
public class RoleBasedBorrowingPolicy implements BorrowingPolicy {

    @Override
    public int getMaxAllowedBooks(Member member) {
        return switch (member.getRole()) {
            case STUDENT -> 2;
            case REGULAR -> 4;
            case PREMIUM -> 8;
        };
    }
}
