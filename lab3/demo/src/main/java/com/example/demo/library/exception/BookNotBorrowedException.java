package com.example.demo.library.exception;

public class BookNotBorrowedException extends LibraryException {

    public BookNotBorrowedException(String memberName, String bookTitle) {
        super("Membrul '" + memberName + "' nu are imprumutata cartea '" + bookTitle + "'.");
    }
}
