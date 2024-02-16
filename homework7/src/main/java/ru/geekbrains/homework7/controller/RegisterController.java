package ru.geekbrains.homework7.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.homework7.database.entity.User;
import ru.geekbrains.homework7.service.NotificationService;
import ru.geekbrains.homework7.service.UserService;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final NotificationService notificationService;

    @PostMapping("/new-user")
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("roles") String roles, Model model) {

        User user = User.builder()
                .username(username)
                .password(password)
                .roles(roles)
                .build();

        notificationService.notify("Register new User " + user.getUsername());
        boolean check = userService.addUser(user);

        if (check) {
            notificationService.notify("Register Success " + user.getUsername());
            model.addAttribute("register", user);
            model.addAttribute("response", "Register Success");
            return "redirect:/";
        } else {
            notificationService.notify("Bad Credentials " + user.getUsername());
            model.addAttribute("register", user);
            model.addAttribute("response", "Bad Credentials");
            return "registration";
        }
    }

    @GetMapping()
    public String registerPage() {
        return "registration";
    }
}
