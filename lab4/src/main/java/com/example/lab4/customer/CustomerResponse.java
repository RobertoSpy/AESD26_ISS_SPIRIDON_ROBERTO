package com.example.lab4.customer;

public record CustomerResponse(
        Long id,
        String name,
        String email,
        String phone,
        String address,
        String coordinates) {

    public static CustomerResponse from(Customer c) {
        return new CustomerResponse(c.getId(), c.getName(), c.getEmail(), c.getPhone(),
                c.getAddress(), c.getCoordinates());
    }
}
