package com.example.lab4.delivery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveries;

    public DeliveryService(DeliveryRepository deliveries) {
        this.deliveries = deliveries;
    }

    @Transactional
    public Delivery create(Delivery delivery) {
        return deliveries.save(delivery);
    }

    @Transactional(readOnly = true)
    public List<Delivery> findAll() {
        return deliveries.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Delivery> findById(Long id) {
        return deliveries.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Delivery> findByCustomer(Long customerId) {
        return deliveries.findByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    public List<Delivery> findBetween(LocalDateTime from, LocalDateTime to) {
        return deliveries.findBetween(from, to);
    }

    public int deleteOlderThan(LocalDateTime before) {
        return deliveries.deleteOlderThan(before);
    }

    @Transactional
    public void delete(Long id) {
        deliveries.deleteById(id);
    }
}
