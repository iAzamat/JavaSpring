package com.example.api.demoapi.service;

import com.example.api.demoapi.model.User;
import com.example.api.demoapi.service.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserService userService;
    private final DataProcessingService dataProcessingService;


    @Autowired
    public RegistrationService(UserService userService, DataProcessingService dataProcessingService) {
        this.userService = userService;
        this.dataProcessingService = dataProcessingService;
    }

    public User registerUser(String name, int age, String email){
        User user = userService.createUser(name, age, email);
        return dataProcessingService.addUser(user);
    }
}
