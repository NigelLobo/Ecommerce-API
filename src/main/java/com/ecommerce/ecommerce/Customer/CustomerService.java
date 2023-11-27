package com.ecommerce.ecommerce.Customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.Address.Address;

@Configuration
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String encodePassword(String rawPassword) {
        return rawPassword;
    }

    public void addNewCustomer(String name, String email, String password, String street, String city) {
        Customer n = new Customer();
        n.setName(name);
        n.setEmail(email);
        n.setPasswordHash(encodePassword(password));

        Address newAddress = new Address(street, city);
        n.setAddress(newAddress);

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
}
