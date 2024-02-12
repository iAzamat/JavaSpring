package com.example.taskservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskControllerImpl implements TaskController {
    @Override
    public ResponseEntity<String> greeting(String taskname) {
        return ResponseEntity.ok("Greetings New Task " + taskname);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getTasks() {
        // Получение списка задач
        return ResponseEntity.ok(generateTaskList());
    }

    private static List<Task> generateTaskList() {
        final List<Task> list = new ArrayList<>();
        list.add(new Task(1, "task1"));
        list.add(new Task(2, "task2"));

        return list;
     }

}
