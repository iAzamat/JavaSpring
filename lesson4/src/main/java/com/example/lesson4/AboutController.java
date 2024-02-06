package com.example.lesson4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String about() {
        return "forward:/about.html";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index.html";
    }
}
