package com.example.demo.library.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Member {

    private final String id;
    private final String name;
    private final MemberRole role;
    private final Set<String> borrowedBookIds = new LinkedHashSet<>();

    public Member(String id, String name, MemberRole role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public MemberRole getRole() { return role; }

    public Set<String> getBorrowedBookIds() {
        return Collections.unmodifiableSet(borrowedBookIds);
    }

    public int getBorrowedCount() {
        return borrowedBookIds.size();
    }

    public boolean hasBorrowed(String bookId) {
        return borrowedBookIds.contains(bookId);
    }

    public void addBorrowedBook(String bookId) {
        borrowedBookIds.add(bookId);
    }

    public void removeBorrowedBook(String bookId) {
        borrowedBookIds.remove(bookId);
    }

    @Override
    public String toString() {
        return name + " (" + role + ", " + getBorrowedCount() + " carti)";
    }
}
