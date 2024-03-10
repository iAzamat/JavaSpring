package ru.gb.homework12.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.homework12.database.entity.Task;
import ru.gb.homework12.model.TaskType;
import ru.gb.homework12.service.TaskService;
import ru.gb.homework12.service.TaskServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskControllerImpl implements ITaskController {
    private final TaskService taskService;

    @GetMapping()
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping()
    public Task createTask(@RequestBody Task task, @RequestParam TaskType type) {
        return taskService.createTask(task, type);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskService.getTaskById(id);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        return taskService.updateTask(id, task);
    }

    @Override
    public void deleteTask(Long id) {
        taskService.deleteTask(id);
    }
}
