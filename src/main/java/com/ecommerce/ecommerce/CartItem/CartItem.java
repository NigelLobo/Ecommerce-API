package com.ecommerce.ecommerce.CartItem;

import java.math.BigDecimal;

import com.ecommerce.ecommerce.Cart.Cart;
import com.ecommerce.ecommerce.Customer.Customer;
import com.ecommerce.ecommerce.Product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private boolean isBought = false;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne // this should probably be OneToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    public CartItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }
}
