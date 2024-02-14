package ru.geekbrains.homework6.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.homework6.Model.TaskStatus;
import ru.geekbrains.homework6.Service.EmployerService;
import ru.geekbrains.homework6.Service.NotificationService;
import ru.geekbrains.homework6.Service.TaskService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TaskWebController {
    private final TaskService service;
    private final NotificationService notificationService;
    private final EmployerService employerService;


    @GetMapping
    public String homePage() {
        return "forward:index.html";
    }

    @GetMapping("/tasks")
    public String tasksPage(Model model) {
        model.addAttribute("tasks", service.findAll());
        return "tasks";
    }

    @GetMapping(value = "/new")
    public String addTask(Model model) {
        model.addAttribute("tasks", service.findAll());
        return "new";
    }

    @PostMapping(value = "/delete")
    public String deleteTask(@RequestParam("id") Long id, Model model) {
        notificationService.notify("Delete was active " + id);
        service.deleteById(id);
        model.addAttribute("tasks", service.findAll());
        return "redirect:/tasks";
    }

    @PostMapping(value = "/edit")
    public String updateTask(@RequestParam("id") Long id,
                             @RequestParam("description") String description,
                             @RequestParam("status") TaskStatus status,
                             Model model) {
        notificationService.notify("Update Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}');
        service.updateById(description, id);
        service.setStatus(status, id);
        model.addAttribute("tasks", service.findAll());
        return "redirect:/tasks";
    }

    @PostMapping(value = "/add")
    public String createTask(@RequestParam("description") String description,
                             Model model) {
        notificationService.notify("Create Task " + service.create(description));
        model.addAttribute("tasks", service.findAll());
        return "redirect:/tasks";
    }

    @PostMapping(value = "/filter")
    public String filterTask(@RequestParam("status") String status, Model model) {
        switch (status) {
            case "no_filter" -> {
                model.addAttribute("tasks", service.findAll());
                model.addAttribute("status", status);
            }
            case "new", "proc", "comp" -> {
                model.addAttribute("tasks", service.getByStatus(TaskStatus.convert(status)));
                model.addAttribute("status", status);
            }
        }
        notificationService.notify("Filter: " + status);
        return "tasks";
    }

    @GetMapping("/employers")
    public String employersPage(Model model) {
        model.addAttribute("employers", employerService.findAll());
        return "employers";
    }

    @PostMapping(value = "/employerdelete")
    public String deleteEmployer(@RequestParam("id") Long id, Model model) {
        notificationService.notify("Delete was active " + id);
        employerService.deleteById(id);
        model.addAttribute("employers", employerService.findAll());
        return "redirect:/employers";
    }

    @PostMapping(value = "/employeredit")
    public String updateEmployer(@RequestParam("id") Long id,
                             @RequestParam("name") String name,
                             Model model) {
        notificationService.notify("Update Employer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}');
        employerService.updateById(name, id);
        model.addAttribute("employers", employerService.findAll());
        return "redirect:/employers";
    }

    @PostMapping(value = "/employeradd")
    public String createEmployer(@RequestParam("name") String name,
                             Model model) {
        notificationService.notify("Create Employer " + employerService.create(name));
        model.addAttribute("employers", employerService.findAll());
        return "redirect:/employers";
    }

    @GetMapping(value = "/new_employer")
    public String addEmployer(Model model) {
        model.addAttribute("employers", employerService.findAll());
        return "new_employer";
    }
}
