package ru.geekbrains.homework6.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.homework6.model.Task;
import ru.geekbrains.homework6.service.TaskService;
import ru.geekbrains.homework6.model.TaskStatus;

import java.util.List;
import java.util.Optional;

@Tag(name = "Task API Controller", description = "Task API Controller")
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @PostMapping(path = "/create")
    public ResponseEntity<Task> createTask(@RequestParam("description") String description) {
        Task task = service.create(description);

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

    @PutMapping(path = "/update/task")
    public ResponseEntity<Task> updateTaskById(@RequestParam("id") Long id, @RequestParam("description") String description) {
        Task task = service.updateById(description, id);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/update/status")
    public ResponseEntity<Task> updateStatus(@RequestParam("id") Long id, @RequestParam("status") String status) {
        Task task = service.setStatus(TaskStatus.convert(status), id);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Task> deleteTask(@RequestParam("id") Long id) {
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

    @GetMapping(path = "/getTask")
    public ResponseEntity<Task> getTaskWithSQL(@RequestParam("tasksId") Long taskId) {
        Optional<Task> task = service.findWithJoinFetch(taskId);
        return task.map(
                value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(
                () -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
