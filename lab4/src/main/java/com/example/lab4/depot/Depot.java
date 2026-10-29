package com.example.lab4.depot;

import java.util.ArrayList;
import java.util.List;

import com.example.lab4.vehicle.Vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "depots")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Depot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String coordinates;

    @OneToMany(mappedBy = "depot")
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Vehicle> vehicles = new ArrayList<>();

    public Depot(String name, String coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }
}
