package com.example.lab4.delivery;

import java.time.LocalDateTime;

import com.example.lab4.customer.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer", nullable = false)
    @ToString.Exclude
    private Customer customer;

    @Column(nullable = false)
    private int quantity;

    private String notes;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    public Delivery(Customer customer, int quantity, String notes) {
        this.customer = customer;
        this.quantity = quantity;
        this.notes = notes;
    }
}
