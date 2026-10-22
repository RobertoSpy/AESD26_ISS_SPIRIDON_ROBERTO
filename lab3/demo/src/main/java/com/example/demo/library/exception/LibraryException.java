package com.example.demo.library.exception;


public abstract class LibraryException extends RuntimeException {

    protected LibraryException(String message) {
        super(message);
    }
}
