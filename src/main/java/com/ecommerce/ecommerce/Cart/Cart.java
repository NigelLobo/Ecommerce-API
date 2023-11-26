package com.ecommerce.ecommerce.Cart;

import java.math.BigDecimal;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import com.ecommerce.ecommerce.CartItem.CartItem;
import com.ecommerce.ecommerce.Customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart")
    private Set<CartItem> items;
    private BigDecimal totalValue;

    @OneToOne(mappedBy = "cart")
    private Customer customer;
}
