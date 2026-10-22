package com.example.demo.library.exception;

public class BookNotAvailableException extends LibraryException {

    public BookNotAvailableException(String bookTitle) {
        super("Cartea '" + bookTitle + "' este deja imprumutata.");
    }
}
