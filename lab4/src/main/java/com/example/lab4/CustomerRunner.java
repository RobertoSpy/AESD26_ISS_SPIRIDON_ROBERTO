package com.example.lab4;

import com.example.lab4.customer.Customer;
import com.example.lab4.customer.CustomerRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class CustomerRunner implements CommandLineRunner {

    private final CustomerRepository customers;

    public CustomerRunner(CustomerRepository customers) {
        this.customers = customers;
    }

    @Override
    public void run(String... args) {
        customers.save(new Customer("Ana Pop", "ana.pop@example.com", "0721000001",
                "Str. Lapusneanu 10, Iasi", "47.1585,27.6014"));
        customers.save(new Customer("Ion Popescu", "ion.popescu@example.com", "0721000002",
                "Bd. Carol I 5, Iasi", "47.1740,27.5720"));

        System.out.println("All customers:");
        customers.findAll().forEach(System.out::println);

        System.out.println("By email: " + customers.findByEmail("ana.pop@example.com").orElse(null));
        System.out.println("Name contains 'pop': " + customers.findByNameContainingIgnoreCase("pop").size());
    }
}
