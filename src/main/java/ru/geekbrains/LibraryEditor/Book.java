package ru.geekbrains.LibraryEditor;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
//@NoArgsConstructor
@AllArgsConstructor
//@Value
//@Builder
public class Book {
    static long counter;
    private long id;
    private String name;

    public Book() {
        this.id = counter;
        this.name = "Book " + this.id;
        counter++;
    }

    public Book(String name) {
        this.id = counter;
        this.name = name;
        counter++;
    }

}
