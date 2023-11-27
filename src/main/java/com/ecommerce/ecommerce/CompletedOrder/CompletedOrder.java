package com.ecommerce.ecommerce.CompletedOrder;

import java.math.BigDecimal;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import com.ecommerce.ecommerce.CartItem.CartItem;
import com.ecommerce.ecommerce.Cart.Cart;
import com.ecommerce.ecommerce.Customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompletedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // @OneToMany() ??
    // private Set<CartItem> itemsBought;

    private BigDecimal amountPaid;
}
