package ru.geekbrains.homework7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.homework7.database.entity.User;
import ru.geekbrains.homework7.database.repository.UserRepository;
import ru.geekbrains.homework7.config.UserDetailsConfig;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        return user.map(UserDetailsConfig::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
