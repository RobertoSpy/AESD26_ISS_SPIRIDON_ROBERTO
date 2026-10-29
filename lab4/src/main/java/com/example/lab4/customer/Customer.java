package com.example.lab4.customer;

import java.util.ArrayList;
import java.util.List;

import com.example.lab4.delivery.Delivery;
import com.example.lab4.person.Person;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends Person {

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String coordinates;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Delivery> deliveries = new ArrayList<>();

    public Customer(String name, String email, String phone, String address, String coordinates) {
        super(name, email, phone);
        this.address = address;
        this.coordinates = coordinates;
    }

    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
        delivery.setCustomer(this);
    }
}
