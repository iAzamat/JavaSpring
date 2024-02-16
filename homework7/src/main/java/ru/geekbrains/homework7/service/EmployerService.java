package ru.geekbrains.homework7.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.homework7.database.entity.Employer;
import ru.geekbrains.homework7.database.entity.Task;
import ru.geekbrains.homework7.database.repository.EmployerRepository;
import ru.geekbrains.homework7.database.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployerService {
    private final EmployerRepository repository;
    private final TaskRepository taskRepository;
    private final NotificationService notificationService;


    public Employer create(String firstname) {
        Employer employer = null;
        if (firstname != null && firstname.length() > 1) {
            employer = new Employer(firstname);
            repository.save(employer);
            notificationService.notify("Create Employer: " + employer);
        } else {
            notificationService.notify("Fail create Employer");
        }
        return employer;
    }

    public List<Employer> findAll() {
        List<Employer> employers = repository.findAll();
        employers.forEach(t -> notificationService.notify("Find all Employers : " + t));
        return employers;
    }

    public Employer findById(Long id) {
        if (id != null) {
            Optional<Employer> employer = repository.findById(id);
            if (employer.isPresent()) {
                notificationService.notify("get Employer: " + employer);
            } else {
                notificationService.notify("get Employer: " + id + " not found in base");
            }
            return employer.orElse(null);
        }
        notificationService.notify("Null Id");
        return null;
    }

    public Long deleteById(Long id) {
        Employer employer = findById(id);
        if (employer != null) {
            repository.delete(employer);
            notificationService.notify("Delete Employer: " + employer);
            return id;
        } else {
            notificationService.notify("Delete Employer: " + id + " not found in base");
        }
        return null;
    }

    public Employer updateById(String name, Long id) {
        if (name != null && name.length() > 1 && id != null) {
            Employer employer = findById(id);

            if (employer != null) {
                employer.setFirstname(name);
                repository.save(employer);
                notificationService.notify("Update Employer data: " + employer);
            } else {
                notificationService.notify("Update Employer data: " + id + " not found in base");
            }
            return employer;
        } else {
            return null;
        }
    }

    @Transactional
    public Employer updateById(List<Task> tasks, Long id) {
        if (id != null) {
            Employer employer = findById(id);

            if (employer != null) {
                tasks.stream()
                        .map(Task::getId)
                        .forEach(taskId -> addToEmployUsingGetById(id, taskId));
                notificationService.notify("Update Employer tasks: " + employer);
            } else {
                notificationService.notify("Update Employer tasks: " + id + " not found in base");
            }
            return employer;
        } else {
            return null;
        }
    }

    @Transactional
    public Task addToEmployUsingGetById(long id, long task_id) {
        Employer employer = repository.findById(id).orElse(null);
        Task task = taskRepository.findById(task_id).orElse(null);

        if (employer != null && task != null) {
            task.addEmployer(employer);
        }
        return task;
    }

    @Transactional
    public Task deleteEmployUsingGetById(long id, long task_id) {
        Employer employer = repository.findById(id).orElse(null);
        Task task = taskRepository.findById(task_id).orElse(null);

        if (employer != null &&
                task != null &&
                employer.getEmployerTasks().contains(task)) {
            employer.removeTask(task);
            notificationService.notify("Remove Task");
        } else {
            notificationService.notify("Error! Remove Task");
        }

        return task;
    }

    public List<Task> getAllTaskById(Long id) {
        if (id != null) {
            return taskRepository.findEmployerTasksByEmployerId(id).orElse(null);
        }
        return null;
    }
}
