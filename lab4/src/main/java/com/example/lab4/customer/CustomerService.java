package com.example.lab4.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customers;

    public CustomerService(CustomerRepository customers) {
        this.customers = customers;
    }

    @Transactional
    public Customer create(Customer customer) {
        return customers.save(customer);
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customers.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Customer> findById(Long id) {
        return customers.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Customer> findByEmail(String email) {
        return customers.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Customer> searchByName(String name) {
        return customers.findByNameContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public List<Customer> findWithDeliveryOfAtLeast(int minQuantity) {
        return customers.findWithDeliveryOfAtLeast(minQuantity);
    }

    @Transactional(readOnly = true)
    public Customer getById(Long id) {
        return customers.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Transactional
    public Customer update(Long id, CustomerRequest request) {
        Customer customer = getById(id);
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customer.setAddress(request.address());
        customer.setCoordinates(request.coordinates());
        return customers.saveAndFlush(customer);
    }

    @Transactional
    public void delete(Long id) {
        customers.delete(getById(id));
    }
}
