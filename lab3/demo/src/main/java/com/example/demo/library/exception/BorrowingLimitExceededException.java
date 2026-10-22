package com.example.demo.library.exception;

public class BorrowingLimitExceededException extends LibraryException {

    public BorrowingLimitExceededException(String memberName, int limit) {
        super("Membrul '" + memberName + "' a atins limita de " + limit + " carti imprumutate.");
    }
}
