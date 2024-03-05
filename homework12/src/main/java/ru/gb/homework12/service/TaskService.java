package ru.gb.homework12.service;

import ru.gb.homework12.database.entity.Task;
import ru.gb.homework12.model.TaskType;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();
    Task createTask(Task task, TaskType type);

    Task getTaskById(Long id);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);
}
