package com.example.testing1.controller;

import com.example.testing1.repository.User;
import com.example.testing1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserControllerImpl implements UserController {
    private final UserService service;

    @Autowired
    public UserControllerImpl(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllBooks() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.save(user);
    }

    @PutMapping("/{id}")
    public User updateBook(@PathVariable Long id, @RequestBody User note) {
        return service.updateUser(id, note);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.delete(id);
    }

    @Override
    public User getUserByEmail(@RequestParam String email) {
        return service.findByEmail(email);
    }
}
