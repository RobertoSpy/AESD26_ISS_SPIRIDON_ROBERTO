package com.example.lab4.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    List<Customer> findByNameContainingIgnoreCase(String name);


    @Query("select distinct c from Customer c join c.deliveries d where d.quantity >= :minQuantity")
    List<Customer> findWithDeliveryOfAtLeast(@Param("minQuantity") int minQuantity);
}
