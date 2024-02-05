package com.example.api.demoapi.controller;

import com.example.api.demoapi.model.User;
import com.example.api.demoapi.service.DataProcessingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Home Controller", description = "Home Controller API")
@RestController
@RequestMapping("/")
public class DataProcessingHomeController {
    private final DataProcessingService dataProcessingService;

    @Autowired
    public DataProcessingHomeController(DataProcessingService dataProcessingService) {
        this.dataProcessingService = dataProcessingService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<User>> homeUsers() {
        return new ResponseEntity<>(dataProcessingService.getUsers(), HttpStatus.OK);
    }
}
