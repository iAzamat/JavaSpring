package com.example.userservice.controller;

import com.example.userservice.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    //@PostMapping("/register")
    //@RequestMapping(value = "/users/register", method = RequestMethod.POST)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        // Код регистрации пользователя
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }

}
