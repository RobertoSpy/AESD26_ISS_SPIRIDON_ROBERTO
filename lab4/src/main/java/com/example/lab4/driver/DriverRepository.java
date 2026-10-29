package com.example.lab4.driver;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByEmail(String email);


    @Query("select d from Driver d where d.vehicle is null")
    List<Driver> findUnassigned();
}
