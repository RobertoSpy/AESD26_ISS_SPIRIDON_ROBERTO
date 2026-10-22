package com.example.demo.library.service;

public interface BorrowingService {

    void borrowBook(String memberId, String bookId);

    void returnBook(String memberId, String bookId);
}
