package com.example.demo.library.exception;

public class MemberNotFoundException extends LibraryException {

    public MemberNotFoundException(String memberId) {
        super("Membrul cu ID-ul '" + memberId + "' nu exista.");
    }
}
