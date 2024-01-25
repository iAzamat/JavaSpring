package ru.geekbrains.LibraryEditor;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Home Controller", description = "Home Controller API")
@RestController
@RequestMapping("/")
public class LibraryHomeController {
    //@GetMapping("/")
    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return LibraryService.update();
    }
}
