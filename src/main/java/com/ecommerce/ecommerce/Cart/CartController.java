package com.ecommerce.ecommerce.Cart;

import java.util.ArrayList;
import java.util.List;

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
import com.ecommerce.ecommerce.Customer.CustomerService;

@Controller
@RequestMapping(path = "/cart")
public class CartController {
    @Autowired
    private final CustomerService customerService;

    public CartController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "/{email}")
    public @ResponseBody ResponseEntity<String> addProductToCart(@PathVariable String email,
            @RequestParam String productName, @RequestParam int quantity) {
        try {
            customerService.addProductToCart(email, productName, quantity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product added to Cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add Product to Cart" + e.toString());
        }
    }

    @GetMapping(path = "/{email}")
    public @ResponseBody Iterable<Cart> getCartByEmail(@PathVariable String email) {
        List<Cart> cartList = new ArrayList();
        cartList.add(customerService.getCart(email));
        return cartList;
    }

    @DeleteMapping(path = "/{email}")
    public @ResponseBody ResponseEntity<String> clearCart(@PathVariable String email) {
        try {
            boolean isCleared = customerService.clearCart(email);
            if (isCleared)
                return ResponseEntity.status(HttpStatus.OK).body("Clear Cart success.");
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart/User does not exist.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

}
