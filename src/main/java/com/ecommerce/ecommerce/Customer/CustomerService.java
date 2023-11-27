package com.ecommerce.ecommerce.Customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.Address.Address;
import com.ecommerce.ecommerce.Cart.Cart;
import com.ecommerce.ecommerce.CartItem.CartItem;
import com.ecommerce.ecommerce.Product.Product;
import com.ecommerce.ecommerce.Product.ProductRepository;

@Configuration
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public String encodePassword(String rawPassword) {
        return rawPassword;
    }

    public void addNewCustomer(String name, String email, String password, String street, String city) {
        Address newAddress = new Address(street, city);
        Customer n = new Customer(name, email, password, newAddress);
        customerRepository.save(n);
    }

    public boolean deleteCustomerByEmail(String email) {
        long recordsDeleted = customerRepository.deleteByEmail(email);
        return recordsDeleted == 1;
    }

    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Iterable<Customer> getCustomer(String email) {
        return customerRepository.findByEmail(email);
    }

    public boolean updateCustomer(String email, Customer updatedCustomer) {
        Customer currCustomer = customerRepository.findFirstByEmail(email);

        if (currCustomer == null)
            return false;

        currCustomer.setAddress(updatedCustomer.getAddress());
        currCustomer.setName(updatedCustomer.getName());
        currCustomer.setPasswordHash(updatedCustomer.getPasswordHash());

        customerRepository.save(currCustomer);
        return true;
    }

    /** * Cart operations */

    public void addProductToCart(String email, String productName, int quantity) {
        Product desiredProduct = productRepository.findFirstByName(productName);
        Customer customer = customerRepository.findFirstByEmail(email);

        CartItem cartItem = new CartItem(quantity, desiredProduct);
        customer.getCart().addItemToCart(cartItem);

        customerRepository.save(customer);
    }

    public Cart getCart(String email) {
        Customer customer = customerRepository.findFirstByEmail(email);
        return customer.getCart();
    }

    public boolean clearCart(String email) {
        Customer customer = customerRepository.findFirstByEmail(email);
        if (customer == null)
            return false;
        customer.getCart().getItems().clear();
        return true;
    }
}
