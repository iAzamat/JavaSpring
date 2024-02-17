package ru.geekbrains.homework8.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.homework8.database.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
