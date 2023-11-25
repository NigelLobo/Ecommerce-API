package com.ecommerce.ecommerce.User;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
// @RequiredArgsConstructor()
public class User {
    @Id
    // @NonNull
    private String email;
    private String name;
    // @NonNull //need final in all vars for this
    private String password;

    // PaymentMethod[] ;
}