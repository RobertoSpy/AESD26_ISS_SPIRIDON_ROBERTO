package com.example.lab4.depot;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepotRepository extends JpaRepository<Depot, Long> {

    Optional<Depot> findByName(String name);
}
