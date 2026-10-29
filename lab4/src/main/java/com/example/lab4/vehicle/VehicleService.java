package com.example.lab4.vehicle;

import java.util.List;
import java.util.Optional;

import com.example.lab4.driver.Driver;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleService {

    private final VehicleRepository vehicles;

    public VehicleService(VehicleRepository vehicles) {
        this.vehicles = vehicles;
    }

    @Transactional
    public Vehicle create(Vehicle vehicle) {
        return vehicles.save(vehicle);
    }

    @Transactional(readOnly = true)
    public List<Vehicle> findAll() {
        return vehicles.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Vehicle> findById(Long id) {
        return vehicles.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Vehicle> findByDepot(Long depotId) {
        return vehicles.findByDepotId(depotId);
    }

    @Transactional(readOnly = true)
    public List<Vehicle> findAvailable() {
        return vehicles.findByAvailableTrue();
    }

    @Transactional(readOnly = true)
    public List<Vehicle> findAvailableWithCapacityAtLeast(int capacity) {
        return vehicles.findAvailableWithCapacityAtLeast(capacity);
    }

    @Transactional
    public Vehicle assignDriver(Long vehicleId, Driver driver) {
        Vehicle vehicle = vehicles.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("No vehicle with id " + vehicleId));
        vehicle.setDriver(driver);
        return vehicle;
    }

    public int setAvailabilityForDepot(Long depotId, boolean available) {
        return vehicles.updateAvailabilityByDepot(depotId, available);
    }

    @Transactional
    public void delete(Long id) {
        vehicles.deleteById(id);
    }
}
