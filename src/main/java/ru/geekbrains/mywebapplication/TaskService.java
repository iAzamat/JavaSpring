package ru.geekbrains.mywebapplication;

import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTask(UUID uuid) {
        return tasks.stream().filter(t -> t.getId().equals(uuid)).findFirst().orElse(null);
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public void deleteTask(UUID uuid) {
        tasks.removeIf(t -> t.getId().equals(uuid));
    }

    public Task updateTask(UUID uuid, Task task) {
        Task task_new = getTask(uuid);
        if (task_new != null) {
            task_new.setDescription(task.getDescription());
            task_new.setName(task.getName());
            task_new.setStatus(task.getStatus());
            task_new.setCompletionTime(task.getCompletionTime());
        }
        return task_new;
    }
}
