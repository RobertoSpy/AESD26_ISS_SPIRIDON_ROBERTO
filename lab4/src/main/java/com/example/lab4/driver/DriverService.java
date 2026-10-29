package com.example.lab4.driver;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DriverService {

    private final DriverRepository drivers;

    public DriverService(DriverRepository drivers) {
        this.drivers = drivers;
    }

    @Transactional
    public Driver create(Driver driver) {
        return drivers.save(driver);
    }

    @Transactional(readOnly = true)
    public List<Driver> findAll() {
        return drivers.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Driver> findById(Long id) {
        return drivers.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Driver> findByEmail(String email) {
        return drivers.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Driver> findUnassigned() {
        return drivers.findUnassigned();
    }

    @Transactional
    public void delete(Long id) {
        drivers.deleteById(id);
    }
}
