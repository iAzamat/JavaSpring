package ru.geekbrains.homework5.Service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.homework5.Model.Task;
import ru.geekbrains.homework5.Model.TaskRepository;
import ru.geekbrains.homework5.Model.TaskStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final NotificationService notificationService;

    public List<Task> getTasks() {
        List<Task> temp = repository.findAll();
        temp.forEach(t -> notificationService.notify("getTasks : " + t.toString()));
        return temp;
    }

    public Optional<Task> createTask(String description) {
        Optional<Task> temp = Optional.of(new Task(description));
        repository.save(temp.get());
        notificationService.notify("createTask: " + temp);
        return temp;
    }

    public Optional<Task> findTaskById(Long id) {
        if (id != null) {
            Optional<Task> temp = Optional.of(repository.getReferenceById(id));
            notificationService.notify("getTaskById: " + temp);
            return temp;
        } else {
            notificationService.notify("Incorrect Task id");
            return Optional.empty();
        }
    }

    public Optional<Task> deleteTaskById(Long id) {
        Optional<Task> temp = findTaskById(id);
        temp.ifPresent(task -> {
            repository.delete(task);
            notificationService.notify("deleteTaskById: " + temp);
        });
        return temp;
    }

    public Optional<Task> updateTaskById(String description, Long id) {
        Optional<Task> temp = findTaskById(id);
        temp.ifPresent(task -> {
            task.setDescription(description);
            repository.save(task);
            notificationService.notify("updateTaskById: " + temp);
        });
        return temp;
    }

    public Optional<Task> setTaskStatus(TaskStatus status, Long id) {
        Optional<Task> temp = findTaskById(id);
        temp.ifPresent(task -> {
            switch (status) {
                case TASK_NEW -> {
                    task.setStatus(status);
                    task.setDateBegin(new Date());
                    task.setDateEnd(null);
                    repository.save(task);
                }
                case TASK_IN_PROCESS -> {
                    task.setStatus(status);
                    repository.save(task);
                }
                case TASK_COMPETED -> {
                    task.setStatus(status);
                    task.setDateEnd(new Date());
                    repository.save(task);
                }
            }
            notificationService.notify("setTaskStatus: " + temp);
        });
        return temp;
    }

    public List<Task> getTasksByStatus(TaskStatus taskStatus) {
        List<Task> temp = repository.findTasksByStatus(taskStatus);
        temp.forEach(t -> notificationService.notify("getTasks : " + t.toString()));
        return temp;
    }

}
