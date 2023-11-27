package com.ecommerce.ecommerce.Customer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    List<Customer> findByEmail(String email);

    @Transactional
    long deleteByEmail(String email);

    Customer findFirstByEmail(String email);

}