package com.example.api.demoapi.controller;

import com.example.api.demoapi.model.User;
import com.example.api.demoapi.service.DataProcessingService;
import com.example.api.demoapi.service.NotificationService;
import com.example.api.demoapi.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

//@Tag(name = "Home Controller", description = "Home Controller API")
@Controller
@RequestMapping("/")
public class DataProcessingHomeController {
    private final DataProcessingService dataProcessingService;
    private final NotificationService notificationService;
    private final RegistrationService registrationService;

    @Autowired
    public DataProcessingHomeController(DataProcessingService dataProcessingService, NotificationService notificationService, RegistrationService registrationService) {
        this.dataProcessingService = dataProcessingService;
        this.notificationService = notificationService;
        this.registrationService = registrationService;
    }

    @RequestMapping(value = "")
    public String homeUsers() {
        return "forward:index.html";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("users", dataProcessingService.getUsers());
        return "list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("users", dataProcessingService.getUsers());
        return "new";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteUser(@RequestParam("id") long id, Model model) {
        notificationService.notify("Delete was active " + id);
        dataProcessingService.deleteUser(id);
        model.addAttribute("users", dataProcessingService.getUsers());
        return "redirect:/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateUser(@RequestParam("id") long id,
                             @RequestParam("name") String name,
                             @RequestParam("age") int age,
                             @RequestParam("email") String email,
                             Model model) {
        notificationService.notify("Update User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}');
        dataProcessingService.updateUser(id, name, age, email);
        model.addAttribute("users", dataProcessingService.getUsers());
        return "redirect:/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createUser(@RequestParam("name") String name,
                             @RequestParam("age") int age,
                             @RequestParam("email") String email,
                             Model model) {
        notificationService.notify("Response from Controller: " + registrationService.registerUser(name, age, email));
        model.addAttribute("users", dataProcessingService.getUsers());
        return "redirect:/list";
    }
}
