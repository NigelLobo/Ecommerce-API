package com.ecommerce.ecommerce.CartItem;

import java.math.BigDecimal;

import com.ecommerce.ecommerce.Cart.Cart;
import com.ecommerce.ecommerce.Customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private boolean isBought = false;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

}
