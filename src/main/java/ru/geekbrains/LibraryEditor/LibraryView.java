package ru.geekbrains.LibraryEditor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LibraryView {
    private static final String HTML_HEAD = "<head> <meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'><link rel='stylesheet' href='/style.css'><script src='/button.js'></script> <title>Library Editor</title> </head>";
    private static final String HTML_START = "<html><body> <header><h2>Library Editor</h2></header>";
    private static final String HTML_END = "</body><footer>" + LocalDateTime.now().getYear() + "</footer></html>";

    public static String draw() {
        StringBuilder page = new StringBuilder();
        page.append(HTML_HEAD);
        page.append(HTML_START);
        for (Book book : LibraryRepository.getBookList()) {
            page.append("<div class='entry'> <p> id: " + book.getId() + " name: " + book.getName() + "</p>");
            page.append("<input type='text' class='name_change_" + book.getId() + "' value='" + book.getName() + "'>" + "</input>");
            page.append("<button class='button_change' onclick='handleChange(" + book.getId() + ")'>change</button>");
            page.append("<button class='button_delete' onclick='handleDelete(" + book.getId() + ")'>delete</button>");
            page.append("</div>");
        }
        page.append("<div class='entry'> <p> new book name: </p>");
        page.append("<input type='text' class='name_change_new'> </input>");
        page.append("<button class='button_add' onclick='handleAdd()'>add</button>");
        page.append("</div>");
        page.append(HTML_END);
        return page.toString();
    }
}
