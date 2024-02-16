package ru.geekbrains.homework7.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.homework7.database.entity.User;
import ru.geekbrains.homework7.database.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public boolean addUser(User user) {
        Optional<User> temp = repository.findByUsername(user.getUsername());
        if (temp.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.save(user);
            return true;
        }
        return false;
    }
}
