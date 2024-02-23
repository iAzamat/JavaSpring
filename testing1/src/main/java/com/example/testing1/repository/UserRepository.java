package com.example.testing1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // SELECT * FROM users WHERE email = email
    Optional<User> findByEmail(String email);
}
