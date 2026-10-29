package com.example.lab4;

import java.time.LocalDateTime;

import com.example.lab4.customer.Customer;
import com.example.lab4.customer.CustomerService;
import com.example.lab4.delivery.Delivery;
import com.example.lab4.delivery.DeliveryService;
import com.example.lab4.depot.Depot;
import com.example.lab4.depot.DepotService;
import com.example.lab4.driver.Driver;
import com.example.lab4.driver.DriverService;
import com.example.lab4.vehicle.Vehicle;
import com.example.lab4.vehicle.VehicleService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ServiceRunner implements CommandLineRunner {

    private final DepotService depots;
    private final DriverService drivers;
    private final VehicleService vehicles;
    private final CustomerService customers;
    private final DeliveryService deliveries;

    public ServiceRunner(DepotService depots, DriverService drivers, VehicleService vehicles,
                         CustomerService customers, DeliveryService deliveries) {
        this.depots = depots;
        this.drivers = drivers;
        this.vehicles = vehicles;
        this.customers = customers;
        this.deliveries = deliveries;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n--- ServiceRunner ---");

        Depot iasi = depots.create(new Depot("Depozit Iasi", "47.1585,27.6014"));
        Depot cluj = depots.create(new Depot("Depozit Cluj", "46.7712,23.6236"));

        Vehicle van = vehicles.create(new Vehicle("Van-1", 800, iasi));
        Vehicle truck = vehicles.create(new Vehicle("Truck-1", 3000, iasi));
        vehicles.create(new Vehicle("Van-2", 900, cluj));

        Driver mihai = drivers.create(new Driver("Mihai Ionescu", "mihai@example.com", "0731000001"));
        drivers.create(new Driver("Elena Marin", "elena@example.com", "0731000002"));
        System.out.println("Unassigned drivers before: " + drivers.findUnassigned());
        vehicles.assignDriver(van.getId(), mihai);
        System.out.println("Unassigned drivers after:  " + drivers.findUnassigned());

        System.out.println("Vehicles in Iasi (derived query): " + vehicles.findByDepot(iasi.getId()));
        System.out.println("Vehicles in depot Iasi (via entity): " + depots.countVehicles(iasi.getId()));
        System.out.println("Available, capacity >= 1000 (JPQL): " + vehicles.findAvailableWithCapacityAtLeast(1000));

        int updated = vehicles.setAvailabilityForDepot(iasi.getId(), false);
        System.out.println("Marked unavailable in Iasi (modifying query): " + updated + " vehicles");
        System.out.println("Still available: " + vehicles.findAvailable());

        Customer ana = customers.findByEmail("ana.pop@example.com").orElseThrow();
        Customer ion = customers.findByEmail("ion.popescu@example.com").orElseThrow();
        Delivery recent = deliveries.create(new Delivery(ana, 50, "fragile"));
        deliveries.create(new Delivery(ana, 200, "pallet"));
        Delivery old = new Delivery(ion, 10, "old order");
        old.setTimestamp(LocalDateTime.now().minusDays(30));
        deliveries.create(old);

        System.out.println("Deliveries of Ana: " + deliveries.findByCustomer(ana.getId()).size());
        System.out.println("Deliveries last 7 days: "
                + deliveries.findBetween(LocalDateTime.now().minusDays(7), LocalDateTime.now().plusDays(1)).size());
        System.out.println("Customers with a delivery >= 100: " + customers.findWithDeliveryOfAtLeast(100));

        int removed = deliveries.deleteOlderThan(LocalDateTime.now().minusDays(7));
        System.out.println("Old deliveries deleted: " + removed + ", remaining: " + deliveries.findAll().size());
        System.out.println("(recent delivery id " + recent.getId() + " kept)");
    }
}
