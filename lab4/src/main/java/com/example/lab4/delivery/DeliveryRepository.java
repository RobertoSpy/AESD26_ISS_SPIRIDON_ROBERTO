package com.example.lab4.delivery;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findByCustomerId(Long customerId);

    @Query("select d from Delivery d where d.timestamp between :from and :to order by d.timestamp")
    List<Delivery> findBetween(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Delivery d where d.timestamp < :before")
    int deleteOlderThan(@Param("before") LocalDateTime before);
}
