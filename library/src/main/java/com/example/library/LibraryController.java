package com.example.library;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Library API")
public class LibraryController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ReaderService readerService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable @Parameter(description = "Product id", example = "1") Long id) {
        return bookService.findById(id);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/books/{id}/reader/{readerId}")
    public Book assignReaderToBook(@PathVariable Long id, @PathVariable Long readerId) {
        return bookService.assignReader(id, readerId);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping("/readers")
    public List<Reader> getAllReaders() {
        return readerService.findAll();
    }

    @PostMapping("/readers")
    public Reader addReader(@RequestBody Reader reader) {
        return readerService.save(reader);
    }

}
