package com.example.demo.library.policy;

import com.example.demo.library.model.Member;


public interface BorrowingPolicy {

    int getMaxAllowedBooks(Member member);

    default boolean canBorrow(Member member) {
        return member.getBorrowedCount() < getMaxAllowedBooks(member);
    }
}
