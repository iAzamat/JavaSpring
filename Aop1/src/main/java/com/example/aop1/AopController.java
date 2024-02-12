package com.example.aop1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AopController {
    private final AopService service;

    @Autowired
    public AopController(AopService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public ResponseEntity<?> getHello(@RequestParam("name") String username) {
        service.someMethod();
        service.potentiallyFailingMethod();
        return ResponseEntity.ok("Hello, " + username);
    }
}
