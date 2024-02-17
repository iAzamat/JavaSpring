package ru.geekbrains.homework8.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.homework8.database.entity.Employer;
import ru.geekbrains.homework8.database.entity.Task;
import ru.geekbrains.homework8.database.entity.TaskStatus;
import ru.geekbrains.homework8.database.repository.EmployerRepository;
import ru.geekbrains.homework8.database.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final NotificationService notificationService;
    private final EmployerRepository employerRepository;

    public List<Task> findAll() {
        List<Task> taskList = repository.findAll();
        taskList.forEach(t -> notificationService.notify("getTasks : " + t));
        return taskList;
    }

    public Task create(String name, String description) {
        if (name != null && name.length() > 1) {
            Task task = new Task(name, description);
            repository.save(task);
            notificationService.notify("CreateTask: " + task);
            return task;
        } else {
            notificationService.notify("Fail createTask");
            return null;
        }
    }

    public Task findById(Long id) {
        if (id != null) {
            Optional<Task> task = repository.findById(id);
            task.ifPresentOrElse(
                    t -> notificationService.notify("getTaskById: " + t),
                    () -> notificationService.notify("getTaskById: " + id + " not found"));
            return task.orElse(null);
        } else {
            notificationService.notify("Incorrect Task id");
            return null;
        }
    }

    @Transactional
    public Task deleteById(Long id) {
        Task task = findById(id);
        if (task != null) {
            boolean checkEmployersListIsEmpty = task.getEmployers().isEmpty();
            if (checkEmployersListIsEmpty) {
                repository.delete(task);
                notificationService.notify("deleteTaskById: " + task);
            } else {
                for (Employer employer : task.getEmployers()) {
                    employer.getEmployerTasks().remove(task);
                }
                task.getEmployers().clear();
                repository.delete(task);
                notificationService.notify("deleteTaskById: " + task);
            }
        }
        return task;
    }

    public Task updateById(String name, String description, Long id) {
        Task task = findById(id);

        if (task != null) {
            task.setName(name);
            task.setDescription(description);
            repository.save(task);
            notificationService.notify("updateTaskById: " + task);
        }

        return task;
    }

    public Task setStatus(TaskStatus status, Long id) {
        Task task = findById(id);

        if (task != null) {
            switch (status) {
                case TASK_NEW -> {
                    task.setStatus(status);
                    task.setDateBegin(LocalDate.now());
                    task.setDateEnd(null);
                    repository.save(task);
                }
                case TASK_IN_PROCESS -> {
                    task.setStatus(status);
                    repository.save(task);
                }
                case TASK_DONE -> {
                    task.setStatus(status);
                    task.setDateEnd(LocalDate.now());
                    repository.save(task);
                }
            }
            notificationService.notify("setTaskStatus: " + task);
        }

        return task;
    }

    public List<Task> getByStatus(TaskStatus taskStatus) {
        List<Task> temp = repository.findTasksByStatus(taskStatus);
        temp.forEach(t -> notificationService.notify("getTasks : " + t.toString()));
        return temp;
    }
}
