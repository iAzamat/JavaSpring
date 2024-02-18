package ru.geekbrains.homework8.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.logging.annotations.MyLog;
import ru.gb.performance.annotations.MyPerformance;
import ru.geekbrains.homework8.database.entity.User;
import ru.geekbrains.homework8.database.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @MyLog
    @MyPerformance
    @Transactional
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
