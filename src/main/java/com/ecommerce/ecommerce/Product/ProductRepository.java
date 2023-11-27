package com.ecommerce.ecommerce.Product;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Transactional
    long deleteByName(String name);

    List<Product> findByName(String name);

    Product findFirstByName(String name);
}
