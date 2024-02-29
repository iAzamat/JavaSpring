package com.lesson12.block1.computerstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public interface ComputerStoreController {
    @PostMapping("/order/{productId}")
    public ResponseEntity<Integer> orderProduct(@PathVariable int productId);
}
