package com.example.api.demoapi.controller;

import com.example.api.demoapi.service.DataProcessingService;
import com.example.api.demoapi.model.User;
import com.example.api.demoapi.service.RegistrationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Data Processing Controller", description = "Data Processing Controller API")
@RestController
@RequestMapping("/api/users")
public class DataProcessingController {
    private final DataProcessingService dataProcessingService;
    private final RegistrationService registrationService;

    @Autowired
    public DataProcessingController(DataProcessingService dataProcessingService, RegistrationService registrationService) {
        this.dataProcessingService = dataProcessingService;
        this.registrationService = registrationService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<User>> homeUsers() {
        return new ResponseEntity<>(dataProcessingService.getUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public ResponseEntity<List<User>> sortUsers() {
        return new ResponseEntity<>(dataProcessingService.sortByName(), HttpStatus.OK);
    }

    @RequestMapping(value = "/filter/{age}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> filterUsersByAge(@PathVariable("age") int age) {
        return new ResponseEntity<>(dataProcessingService.filterByAge(age), HttpStatus.OK);
    }

    @RequestMapping(value = "/average", method = RequestMethod.POST)
    public ResponseEntity<Double> averageAge() {
        return new ResponseEntity<>(dataProcessingService.calculateAverageAge(), HttpStatus.OK);
    }

    // Создание User через Body
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<List<User>> createUser(@RequestBody User user) {
        registrationService.registerUser(user.getName(), user.getAge(), user.getEmail());
        return new ResponseEntity<>(dataProcessingService.getUsers(), HttpStatus.CREATED);
    }

    // Создание через pathVariable
    @RequestMapping(value = "/create/{name}/{age}/{email}", method = RequestMethod.POST)
    public ResponseEntity<List<User>> createUserParam(@PathVariable("name") String name, @PathVariable("age") int age, @PathVariable("email") String email) {
        registrationService.registerUser(name, age, email);
        return new ResponseEntity<>(dataProcessingService.getUsers(), HttpStatus.CREATED);
    }

    // Создание через параметры /register?name=user,age=18,email=test@test
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<List<User>> registerNewUser(@RequestParam("name") String name, @RequestParam("age") int age, @RequestParam("email") String email) {
        registrationService.registerUser(name, age, email);
        dataProcessingService.sortByName();
        dataProcessingService.calculateAverageAge();
        return new ResponseEntity<>(dataProcessingService.getUsers(), HttpStatus.CREATED);
    }

}
