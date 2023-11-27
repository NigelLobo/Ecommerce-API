package com.ecommerce.ecommerce.Address;

import java.math.BigDecimal;

import com.ecommerce.ecommerce.Customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.NonFinal;

@Entity
@Getter
@Setter
// @RequiredArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @NonNull
    private String street;
    // @NonNull
    private String city;
    // @OneToOne(mappedBy = "address")
    // // @NonNull
    // private Customer customer;

    // public Address(String street, String city, Customer customer) {
    // this.street = street;
    // this.city = city;
    // this.customer = customer;
    // }
    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

}