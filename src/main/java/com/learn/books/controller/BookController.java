package com.learn.books.controller;


import com.learn.books.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController() {
        initializeBooks();
    }

    private void initializeBooks() {
        books.addAll(List.of(
                new Book("Title one", "John", "Comedy"),
                new Book("Title Two", "Chen", "Action"),
                new Book("Title Three", "Sebrina", "Action")
        ));
    }

    @GetMapping("/api/books")
    public List<Book> getBooks () {
        return books;
    }
}
