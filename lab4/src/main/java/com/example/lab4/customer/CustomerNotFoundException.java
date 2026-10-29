package com.example.lab4.customer;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id) {
        super("No customer with id " + id);
    }
}
