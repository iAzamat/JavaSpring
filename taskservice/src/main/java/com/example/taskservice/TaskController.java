package com.example.taskservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/tasks")
public interface TaskController {
    @GetMapping("/greeting/{newtask}")
    ResponseEntity<String> greeting(@PathVariable("newtask") String taskname);

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getTasks();
}
