package com.ecommerce.ecommerce.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.ecommerce.Address.Address;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "")
    public @ResponseBody ResponseEntity<String> addNewCustomer(@RequestParam String name, @RequestParam String email,
            @RequestParam String password, @RequestParam String street, @RequestParam String city) {
        try {
            customerService.addNewCustomer(name, email, password, street, city);
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer creation success.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer creation failed." + e.toString());
        }
    }

    @GetMapping(path = "")
    public @ResponseBody Iterable<Customer> getAllCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(path = "/{email}")
    public @ResponseBody Iterable<Customer> getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomer(email);
    }

    @PutMapping(path = "/{email}")
    public @ResponseBody ResponseEntity<String> updateCustomer(@PathVariable String email, @RequestParam String name,
            @RequestParam String password, @RequestParam String street, @RequestParam String city) {
        try {
            Customer newCustomer = new Customer();

            Address newAddress = new Address(street, city);
            newCustomer.setAddress(newAddress);
            newCustomer.setEmail(email);
            newCustomer.setPasswordHash(password);
            ;
            newCustomer.setName(name);

            boolean result = customerService.updateCustomer(email, newCustomer);
            if (result)
                return ResponseEntity.status(HttpStatus.OK).body("Update Customer success.");
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer does not exist.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @DeleteMapping(path = "/{email}")
    public @ResponseBody ResponseEntity<String> deleteCustomer(@PathVariable String email) {
        try {
            boolean isDeleted = customerService.deleteCustomerByEmail(email);
            if (isDeleted)
                return ResponseEntity.status(HttpStatus.OK).body("Delete Customer success.");
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer does not exist.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

}