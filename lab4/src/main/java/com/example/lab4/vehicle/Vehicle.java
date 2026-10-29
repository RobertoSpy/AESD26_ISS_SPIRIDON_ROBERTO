package com.example.lab4.vehicle;

import com.example.lab4.depot.Depot;
import com.example.lab4.driver.Driver;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int capacity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "depot", nullable = false)
    @ToString.Exclude
    private Depot depot;

    @Column(nullable = false)
    private boolean available = true;

    @OneToOne
    @JoinColumn(name = "driver", unique = true)
    @ToString.Exclude
    private Driver driver;

    public Vehicle(String name, int capacity, Depot depot) {
        this.name = name;
        this.capacity = capacity;
        this.depot = depot;
    }
}
