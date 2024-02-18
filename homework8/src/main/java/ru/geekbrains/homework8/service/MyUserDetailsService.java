package ru.geekbrains.homework8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.logging.annotations.MyLog;
import ru.gb.performance.annotations.MyPerformance;
import ru.geekbrains.homework8.database.entity.User;
import ru.geekbrains.homework8.database.repository.UserRepository;
import ru.geekbrains.homework8.config.UserDetailsConfig;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @MyLog
    @MyPerformance
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        return user.map(UserDetailsConfig::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
