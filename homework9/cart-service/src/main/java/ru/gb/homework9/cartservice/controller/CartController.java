package ru.gb.homework9.cartservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    @GetMapping("/test")
    public String test() {
        return "cartservice";
    }
}
