package ru.gb.homework10.service;

import ru.gb.homework10.database.entity.Session;
import ru.gb.homework10.database.entity.User;

import java.util.Optional;

public interface AuthService {
    Session login(String username, String password);

    String register(User user);

    void logout(Long userId);
}
