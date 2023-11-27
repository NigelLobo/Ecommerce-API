package com.ecommerce.ecommerce.Customer;

import com.ecommerce.ecommerce.Address.Address;
import com.ecommerce.ecommerce.Cart.Cart;
import com.ecommerce.ecommerce.CompletedOrder.CompletedOrder;

import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String email;

    private String name;
    private String passwordHash;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @OneToMany(mappedBy = "customer")
    private Set<CompletedOrder> orderHistory;

    public Customer(String email, String name, String passwordHash, Address address) {
        this.email = email;
        this.name = name;
        this.passwordHash = passwordHash;
        this.address = address;
        this.cart = new Cart();
    }
}