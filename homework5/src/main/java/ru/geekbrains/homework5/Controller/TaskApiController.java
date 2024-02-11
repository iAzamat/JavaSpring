package ru.geekbrains.homework5.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.homework5.Model.Task;
import ru.geekbrains.homework5.Service.TaskService;
import ru.geekbrains.homework5.Model.TaskStatus;

import java.util.List;
import java.util.Optional;

@Tag(name = "Task API Controller", description = "Task API Controller")
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskApiController {

    private final TaskService service;

    @PostMapping(path = "/create")
    public Optional<Task> createTask(@RequestParam("description") String description) {
        return service.createTask(description);
    }

    @GetMapping()
    public List<Task> getTasks() {
        return service.getTasks();
    }

    @GetMapping(path = "/{id}")
    public Optional<Task> getTasksById(@PathVariable("id") Long id) {
        return service.findTaskById(id);
    }

    @PutMapping(path = "/update/task")
    public Optional<Task> updateTaskById(@RequestParam("id") Long id, @RequestParam("description") String description) {
        return service.updateTaskById(description, id);
    }

    @PutMapping(path = "/update/status")
    public Optional<Task> updateStatus(@RequestParam("id") Long id, @RequestParam("status") String status) {
        return service.setTaskStatus(TaskStatus.convert(status), id);
    }

    @DeleteMapping(path = "/delete")
    public Optional<Task> deleteTask(@RequestParam("id") Long id) {
        return service.deleteTaskById(id);
    }

    @GetMapping(path = "/filter")
    public List<Task> selectByStatus(@RequestParam("status") String status) {
        return service.getTasksByStatus(TaskStatus.convert(status));
    }
}
