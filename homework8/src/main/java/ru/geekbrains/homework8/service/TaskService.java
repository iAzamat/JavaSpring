package ru.geekbrains.homework8.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.logging.annotations.MyLog;
import ru.gb.performance.annotations.MyPerformance;
import ru.geekbrains.homework8.database.entity.Employer;
import ru.geekbrains.homework8.database.entity.Task;
import ru.geekbrains.homework8.database.entity.TaskStatus;
import ru.geekbrains.homework8.database.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final NotificationService notificationService;

    @MyLog
    @MyPerformance
    public List<Task> findAll() {
        List<Task> taskList = repository.findAll();
        taskList.forEach(t -> notificationService.notify("getTasks : " + t, 1));
        return taskList;
    }

    @MyLog
    @MyPerformance
    @Transactional
    public Task create(String name, String description) {
        if (name != null && name.length() > 1) {
            Task task = new Task(name, description);
            repository.save(task);
            notificationService.notify("CreateTask: " + task, 1);
            return task;
        } else {
            notificationService.notify("Fail createTask", 3);
            return null;
        }
    }

    @MyLog
    @MyPerformance
    public Task findById(Long id) {
        if (id != null) {
            Optional<Task> task = repository.findById(id);
            task.ifPresentOrElse(
                    t -> notificationService.notify("getTaskById: " + t, 1),
                    () -> notificationService.notify("getTaskById: " + id + " not found", 2));
            return task.orElse(null);
        } else {
            notificationService.notify("Incorrect Task id", 3);
            return null;
        }
    }

    @MyLog
    @MyPerformance
    @Transactional
    public Task deleteById(Long id) {
        Task task = findById(id);
        if (task != null) {
            boolean checkEmployersListIsEmpty = task.getEmployers().isEmpty();
            if (checkEmployersListIsEmpty) {
                repository.delete(task);
                notificationService.notify("deleteTaskById: " + task, 2);
            } else {
                for (Employer employer : task.getEmployers()) {
                    employer.getEmployerTasks().remove(task);
                }
                task.getEmployers().clear();
                repository.delete(task);
                notificationService.notify("deleteTaskById: " + task, 2);
            }
        }
        return task;
    }

    @MyLog
    @MyPerformance
    @Transactional
    public Task updateById(String name, String description, Long id) {
        Task task = findById(id);

        if (task != null) {
            task.setName(name);
            task.setDescription(description);
            repository.save(task);
            notificationService.notify("updateTaskById: " + task, 2);
        }

        return task;
    }

    @MyLog
    @MyPerformance
    @Transactional
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
            notificationService.notify("setTaskStatus: " + task, 2);
        }

        return task;
    }

    @MyLog
    @MyPerformance
    public List<Task> getByStatus(TaskStatus taskStatus) {
        List<Task> temp = repository.findTasksByStatus(taskStatus);
        temp.forEach(t -> notificationService.notify("getTasks : " + t.toString(), 1));
        return temp;
    }
}
