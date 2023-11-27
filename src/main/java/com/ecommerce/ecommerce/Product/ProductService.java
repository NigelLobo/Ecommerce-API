package com.ecommerce.ecommerce.Product;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.ecommerce.Customer.Customer;

@Configuration
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addNewProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        productRepository.save(product);
    }

    public boolean deleteProductByName(String name) {
        long recordsDeleted = productRepository.deleteByName(name);
        return recordsDeleted == 1;
    }

    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    public Iterable<Product> getProduct(String name) {
        return productRepository.findByName(name);
    }

    public boolean updateProduct(String name, BigDecimal price) {
        Product currProduct = productRepository.findFirstByName(name);

        if (currProduct == null)
            return false;

        currProduct.setPrice(price);
        productRepository.save(currProduct);
        return true;
    }
}
