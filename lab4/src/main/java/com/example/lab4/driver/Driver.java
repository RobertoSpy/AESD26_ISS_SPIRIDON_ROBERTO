package com.example.lab4.driver;

import com.example.lab4.person.Person;
import com.example.lab4.vehicle.Vehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "drivers")
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Driver extends Person {


    @OneToOne(mappedBy = "driver")
    @ToString.Exclude
    private Vehicle vehicle;

    public Driver(String name, String email, String phone) {
        super(name, email, phone);
    }
}
