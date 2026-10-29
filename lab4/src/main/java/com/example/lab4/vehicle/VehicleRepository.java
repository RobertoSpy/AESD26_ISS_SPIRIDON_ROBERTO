package com.example.lab4.vehicle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {


    List<Vehicle> findByDepotId(Long depotId);

    List<Vehicle> findByAvailableTrue();


    @Query("select v from Vehicle v where v.available = true and v.capacity >= :capacity order by v.capacity")
    List<Vehicle> findAvailableWithCapacityAtLeast(@Param("capacity") int capacity);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Vehicle v set v.available = :available where v.depot.id = :depotId")
    int updateAvailabilityByDepot(@Param("depotId") Long depotId, @Param("available") boolean available);
}
