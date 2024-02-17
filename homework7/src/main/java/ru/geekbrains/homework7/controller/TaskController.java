package ru.geekbrains.homework7.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.homework7.database.entity.Task;
import ru.geekbrains.homework7.service.TaskService;
import ru.geekbrains.homework7.database.entity.TaskStatus;

import java.util.List;
import java.util.Optional;

@Tag(name = "Task API Controller", description = "Task API Controller")
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestParam("name") String name,
                                           @RequestParam("description") String description) {
        Task task = service.create(name, description);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks = service.findAll();

        if (tasks != null) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Task> getTasksById(@PathVariable("id") Long id) {
        Task task = service.findById(id);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/{id}/")
    public ResponseEntity<Task> updateTaskById(@PathVariable("id") Long id,
                                               @RequestParam("name") String name,
                                               @RequestParam("description") String description) {
        Task task = service.updateById(name, description, id);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/{id}/status/")
    public ResponseEntity<Task> updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        Task task = service.setStatus(TaskStatus.convert(status), id);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Long id) {
        Task task = service.deleteById(id);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<List<Task>> selectByStatus(@RequestParam("status") String status) {
        List<Task> tasks = service.getByStatus(TaskStatus.convert(status));

        if (tasks != null) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
