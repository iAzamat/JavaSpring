package ru.geekbrains.homework8.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.homework8.database.entity.Employer;
import ru.geekbrains.homework8.database.entity.Task;
import ru.geekbrains.homework8.database.entity.TaskStatus;
import ru.geekbrains.homework8.service.EmployerService;
import ru.geekbrains.homework8.service.NotificationService;
import ru.geekbrains.homework8.service.TaskService;

import java.util.Comparator;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TaskWebController {
    private final TaskService service;
    private final NotificationService notificationService;
    private final EmployerService employerService;

    @GetMapping
    public String homePage() {
        return "index";
    }

    @GetMapping("/tasks")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String tasksPage(Model model) {
        model.addAttribute("tasks", service.findAll()
                .stream().sorted(Comparator.comparing(Task::getId)));
        return "tasks";
    }

    @GetMapping(value = "/new")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addTask(Model model) {
        model.addAttribute("tasks", service.findAll());
        return "new";
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteTask(@RequestParam("id") Long id, Model model) {
        notificationService.notify("Delete was active " + id, 2);
        service.deleteById(id);
        model.addAttribute("tasks", service.findAll());
        return "redirect:/tasks";
    }

    @PostMapping(value = "/edit")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateTask(@RequestParam("id") Long id,
                             @RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("status") TaskStatus status,
                             Model model) {
        notificationService.notify("Update Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}', 2);
        service.updateById(name, description, id);
        service.setStatus(status, id);
        model.addAttribute("tasks", service.findAll());
        return "redirect:/tasks";
    }

    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String createTask(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             Model model) {
        notificationService.notify("Create Task " + service.create(name, description), 1);
        model.addAttribute("tasks", service.findAll());
        return "redirect:/tasks";
    }

    @PostMapping(value = "/filter")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String filterTask(@RequestParam("status") String status, Model model) {
        switch (status) {
            case "no_filter" -> {
                model.addAttribute("tasks", service.findAll());
                model.addAttribute("status", status);
            }
            case "new", "proc", "done" -> {
                model.addAttribute("tasks", service.getByStatus(TaskStatus.convert(status)));
                model.addAttribute("status", status);
            }
        }
        notificationService.notify("Filter: " + status, 1);
        return "tasks";
    }

    @GetMapping("/employers")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String employersPage(Model model) {
        model.addAttribute("employers", employerService.findAll()
                .stream().sorted(Comparator.comparing(Employer::getId)));
        return "employers";
    }

    @PostMapping(value = "/employerdelete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteEmployer(@RequestParam("id") Long id, Model model) {
        notificationService.notify("Delete was active " + id, 2);
        employerService.deleteById(id);
        model.addAttribute("employers", employerService.findAll());
        return "redirect:/employers";
    }

    @PostMapping(value = "/employeredit")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateEmployer(@RequestParam("id") Long id,
                                 @RequestParam("firstname") String firstname,
                                 Model model) {
        notificationService.notify("Update Employer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                '}', 2);
        employerService.updateById(firstname, id);
        model.addAttribute("employers", employerService.findAll());
        return "redirect:/employers";
    }

    @PostMapping(value = "/employeradd")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String createEmployer(@RequestParam("firstname") String firstname,
                                 Model model) {
        notificationService.notify("Create Employer " + employerService.create(firstname), 1);
        model.addAttribute("employers", employerService.findAll());
        return "redirect:/employers";
    }

    @GetMapping(value = "/new_employer")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addEmployer(Model model) {
        model.addAttribute("employers", employerService.findAll());
        return "new_employer";
    }
}
