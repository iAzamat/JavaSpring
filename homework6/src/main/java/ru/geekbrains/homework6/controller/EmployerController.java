package ru.geekbrains.homework6.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.homework6.model.Employer;
import ru.geekbrains.homework6.model.Task;
import ru.geekbrains.homework6.service.EmployerService;
import ru.geekbrains.homework6.service.TaskService;

import java.util.List;

@Tag(name = "Employer API Controller", description = "Employer API Controller")
@RestController
@RequestMapping("/api/employers")
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;
    private final TaskService taskService;

    @GetMapping("")
    public ResponseEntity<List<Employer>> getEmployees() {
        List<Employer> employerList = employerService.findAll();

        if (employerList != null) {
            return new ResponseEntity<>(employerList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Employer> getEmployerById(@PathVariable("id") Long id) {
        Employer employer = employerService.findById(id);

        if (employer != null) {
            return new ResponseEntity<>(employer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Employer> createEmployer(@RequestParam("name") String name) {
        Employer employer = employerService.create(name);

        if (employer != null) {
            return new ResponseEntity<>(employer, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Employer> deleteEmployerById(@RequestParam("id") Long id) {
        Employer employer = employerService.deleteById(id);

        if (employer != null) {
            return new ResponseEntity<>(employer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Employer> updateEmployerById(@RequestParam("id") Long id, @RequestParam("name") String name) {
        Employer employer = employerService.updateById(name, id);

        if (employer != null) {
            return new ResponseEntity<>(employer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/assignTasks")
    public ResponseEntity<Employer> updateEmployerTask(@RequestParam("employerId") Long id,
                                                       @RequestParam("tasksId") List<Long> tasksId) {
        Employer employer = employerService.findById(id);

        if (employer != null) {
            List<Task> validTasks = tasksId.stream().filter(taskId -> (taskService.findById(taskId)) != null)
                    .map(taskService::findById).toList();
            employerService.updateById(validTasks, id);
            employer = employerService.findById(id);
            return new ResponseEntity<>(employer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping(path = "/addTask")
    public ResponseEntity<Task> addEmployerTask(@RequestParam("employerId") Long id, @RequestParam("tasksId") Long taskId) {
        if (id != null && taskId != null) {
            Employer employer = employerService.findById(id);
            Task task = taskService.findById(taskId);
            if (employer != null && task != null) {
                Task temp = employerService.addToEmployUsingGetById(id, taskId);
                return new ResponseEntity<>(temp, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = "/removeTask")
    public ResponseEntity<Task> removeEmployerTask(@RequestParam("employerId") Long id, @RequestParam("tasksId") Long taskId) {
        if (id != null && taskId != null) {
            Employer employer = employerService.findById(id);
            Task task = taskService.findById(taskId);
            if (employer != null && task != null) {
                Task temp = employerService.deleteEmployUsingGetById(id, taskId);
                return new ResponseEntity<>(temp, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllTask")
    public ResponseEntity<List<Task>> getAllTaskById(@RequestParam("employerId") Long id) {
        List<Task> taskList = employerService.getAllTaskById(id);
        if (taskList != null) {
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
