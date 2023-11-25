package com.ecommerce.ecommerce.Product;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findByEmail(String email);

    @Transactional
    long deleteByEmail(String email);

}
