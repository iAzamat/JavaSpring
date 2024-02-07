package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        userRepository.save(new User(null, "John", "ivan@example.com"));
        userRepository.save(new User(null, "Kevin", "JaneDoe@example.com"));
        userRepository.save(new User(null, "Kate", "polzovatel@example.com"));
        return userRepository.findAll();
    }

    public User getUserByID(Long id) {
        return userRepository.findById(id).get();
    }
}
