package com.example.testing1.controller;

import com.example.testing1.repository.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface UserController {
    @GetMapping
    public List<User> getAllBooks();

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id);

    @PostMapping
    public User createUser(@RequestBody User user);

    @PutMapping("/{id}")
    public User updateBook(@PathVariable Long id, @RequestBody User user);

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id);

    @GetMapping("/request")
    public User getUserByEmail(@PathVariable String email);
}
