package com.example.lab4.depot;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepotService {

    private final DepotRepository depots;

    public DepotService(DepotRepository depots) {
        this.depots = depots;
    }

    @Transactional
    public Depot create(Depot depot) {
        return depots.save(depot);
    }

    @Transactional(readOnly = true)
    public List<Depot> findAll() {
        return depots.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Depot> findById(Long id) {
        return depots.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Depot> findByName(String name) {
        return depots.findByName(name);
    }

    @Transactional(readOnly = true)
    public int countVehicles(Long id) {
        return depots.findById(id).map(d -> d.getVehicles().size()).orElse(0);
    }

    @Transactional
    public void delete(Long id) {
        depots.deleteById(id);
    }
}
