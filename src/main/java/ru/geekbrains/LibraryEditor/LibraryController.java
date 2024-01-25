package ru.geekbrains.LibraryEditor;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Library Controller", description = "Library Controller API")
@RestController
@RequestMapping("/api/books")
public class LibraryController {
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getItems(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        List<Book> items = LibraryService.getItemsFromDatabase(page, size);
        if (items == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getItem(@PathVariable("id") Long id) {
        Book book = LibraryService.getBookById(id);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
    }

    //@PostMapping("/api/books/add")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        LibraryService.add(book);
        return new ResponseEntity<>("Книга успешно добавлена", HttpStatus.CREATED);
    }

    //    @DeleteMapping("/api/books/delete/{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        LibraryService.delete(id);
        return new ResponseEntity<>("Книга c id: " + id + " успешно удалена", HttpStatus.NO_CONTENT);
    }

    //    @PutMapping("/api/books/update/{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        LibraryService.change(id, book);
        return new ResponseEntity<>("Книга c id: " + id + " успешно обновлена", HttpStatus.OK);
    }

//    @ExceptionHandler(ItemNotFoundException.class)
//    public ResponseEntity<String> handleItemNotFoundException(ItemNotFoundException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
}
