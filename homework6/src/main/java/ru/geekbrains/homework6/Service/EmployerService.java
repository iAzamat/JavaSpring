package ru.geekbrains.homework6.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.homework6.Model.Employer;
import ru.geekbrains.homework6.Model.Task;
import ru.geekbrains.homework6.Model.EmployerRepository;
import ru.geekbrains.homework6.Model.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployerService {
    private final EmployerRepository repository;
    private final TaskRepository taskRepository;
    private final NotificationService notificationService;


    public Employer create(String name) {
        Employer employer = null;
        if (name != null && name.length() > 1) {
            employer = new Employer(name);
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

    public Employer deleteById(Long id) {
        Employer employer = findById(id);
        if (employer != null) {
            repository.delete(employer);
            notificationService.notify("Delete Employer: " + employer);
        } else {
            notificationService.notify("Delete Employer: " + id + " not found in base");
        }
        return employer;
    }

    public Employer updateById(String name, Long id) {
        if (name != null && name.length() > 1 && id != null) {
            Employer employer = findById(id);

            if (employer != null) {
                employer.setName(name);
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
        Employer employer = repository.getReferenceById(id);

        Task task = taskRepository.getReferenceById(task_id);
        task.setEmployer(employer);
        task = taskRepository.save(task);
        return task;
    }

    @Transactional
    public Task deleteEmployUsingGetById(long id, long task_id) {
        Employer employer = repository.getReferenceById(id);
        Task task = taskRepository.getReferenceById(task_id);

        if (task.getEmployer().equals(employer)) {
            task.setEmployer(null);
            task = taskRepository.save(task);
            notificationService.notify("Remove Task");
        } else {
            notificationService.notify("Error! Remove Task");
        }

        return task;
    }

    public List<Task> getAllTaskById(Long id) {
        if (id != null) {
            return taskRepository.findTasksByEmployer_Id(id).orElse(null);
        }
        return null;
    }
}
