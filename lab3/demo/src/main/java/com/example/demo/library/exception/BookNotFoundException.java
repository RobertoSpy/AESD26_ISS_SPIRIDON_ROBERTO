package com.example.demo.library.exception;

public class BookNotFoundException extends LibraryException {

    public BookNotFoundException(String bookId) {
        super("Cartea cu ID-ul '" + bookId + "' nu exista.");
    }
}
