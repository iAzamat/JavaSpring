package ru.gb.homework12.controller;

import org.springframework.web.bind.annotation.*;
import ru.gb.homework12.database.entity.Task;
import ru.gb.homework12.model.TaskType;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public interface ITaskController {
    @GetMapping()
    List<Task> getAllTasks();

    @PostMapping()
    Task createTask(@RequestBody Task task, @RequestParam TaskType type);

    @GetMapping("/{id}")
    Task getTaskById(@PathVariable Long id);

    @PutMapping("/{id}")
    Task updateTask(@PathVariable Long id, @RequestBody Task task);

    @DeleteMapping("/{id}")
    void deleteTask(@PathVariable Long id);
}
