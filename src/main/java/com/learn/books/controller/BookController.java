package com.learn.books.controller;


import com.learn.books.entity.Book;
import org.springframework.web.bind.annotation.*;

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
    public List<Book> getBooks (@RequestParam(required = false) String category) {

        if (category == null) {
            return books;
        }

       return books.stream().filter(book -> book.getCategory().equalsIgnoreCase(category)).toList();
    }




    @GetMapping("/api/books/{title}")
    public Book getBookByTitle (@PathVariable String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;

//        return books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);


    }

    @PostMapping("/api/books")
    public void createBook(@RequestBody Book newbook) {

        boolean isNewBook = books.stream().noneMatch(book -> book.getTitle().equalsIgnoreCase(newbook.getTitle()));

        if (isNewBook) {
            books.add(newbook);
        }


    }

    @PutMapping("/api/books/{title}")
    public void updateBook(@PathVariable String title, @RequestBody Book newbook) {
        for(int i=0; i<books.size(); i++) {
            if(books.get(i).getTitle().equalsIgnoreCase(title)) {
                books.set(i, newbook);
                return;
            }
        }
    }


}
