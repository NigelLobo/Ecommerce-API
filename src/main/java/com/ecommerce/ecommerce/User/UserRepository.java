package com.ecommerce.ecommerce.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, String> {
    List<User> findByEmail(String email);

    @Transactional
    long deleteByEmail(String email);

}