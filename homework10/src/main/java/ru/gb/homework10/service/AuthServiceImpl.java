package ru.gb.homework10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.homework10.database.entity.Session;
import ru.gb.homework10.database.entity.User;
import ru.gb.homework10.database.repository.SessionRepository;
import ru.gb.homework10.database.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Session login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            Session session = new Session();
            session.setUserId(user.get().getId());
            sessionRepository.save(session);
            return session;
        }

        return null;
    }

    @Override
    public String register(User newUser) {
        Optional<User> user = userRepository.findByUsername(newUser.getUsername());
        if (user.isPresent()) {
            throw new RuntimeException("User exists in database");
        } else {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userRepository.save(newUser);
            return newUser.getUsername();
        }
    }

    @Override
    public void logout(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<Session> session = sessionRepository.findByUserId(userId);
            session.ifPresent(value -> sessionRepository.deleteById(value.getId()));
        }
    }
}
