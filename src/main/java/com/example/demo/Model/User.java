package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    private Long id;
    private String name;
    private String email;
}
