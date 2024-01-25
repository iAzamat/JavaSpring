package ru.geekbrains.LibraryEditor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope("singleton")
public class LibraryRepository {
    private static ArrayList<Book> bookList = new ArrayList<>();

    public LibraryRepository() {
        for (int i = 1; i <= 10; i++) {
            bookList.add(new Book());
        }
    }

    public static Book getBookById(Long id) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public static ArrayList<Book> getBookList() {
        return bookList;
    }

    public static void addBook(Book newBook) {
        bookList.add(newBook);
    }

    public static void removeBook(long id) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                bookList.remove(book);
                break;
            }
        }
    }

    public static void changeBook(long id, Book newBook) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getId() == id) {
                newBook.setId(id);
                bookList.set(i, newBook);
                Book.counter = Book.counter - 1;
                break;
            }
        }
    }

    public static List<Book> getItemsFromDatabase(int page, int size) {
        if (page <= 0) {
            return null;
        } else {
            int startIndex = size * (page - 1);
            return bookList.
                    stream().
                    skip(startIndex).
                    limit(size).
                    toList();
        }
    }
}
