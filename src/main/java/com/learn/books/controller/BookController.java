package com.learn.books.controller;


import com.learn.books.entity.Book;
import com.learn.books.request.BookRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController() {
        initializeBooks();
    }

    private void initializeBooks() {
        books.addAll(List.of(
                new Book(1,"Computer Science Pro", "John", "Computer Science", 5),
                new Book(2,"Java for Beginning", "Ahmed", "Computer Science", 4),
                new Book(3,"Javascript For Kids", "Jonathan", "Computer Science", 2),
                new Book(4,"Pirates", "Eleska", "Fantasy", 5),
                new Book(5,"How to Become Sigma Male", "Kolanda", "Comedy", 5)

        ));
    }

    @GetMapping
    public List<Book> getBooks (@RequestParam(required = false) String category) {

        if (category == null) {
            return books;
        }

       return books.stream().filter(book -> book.getCategory().equalsIgnoreCase(category)).toList();
    }




    @GetMapping("/{id}")
    public Book getBookById (@PathVariable long id) {
//        for (Book book : books) {
//            if (book.getId() == id) {
//                return book;
//            }
//        }
//        return null;

        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);


    }

    @PostMapping
    public void createBook(@RequestBody BookRequest bookRequest) {
        long id = books.isEmpty() ?  1 :  books.get(books.size()-1).getId() +1;
        

        Book book = convertToBook(id, bookRequest);

        books.add(book);

    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable long id, @RequestBody Book newbook) {
        for(int i=0; i<books.size(); i++) {
            if(books.get(i).getId() == id) {
                books.set(i, newbook);
                return;
            }
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        books.removeIf(book -> book.getId() == id);
    }


    private Book convertToBook (long id, BookRequest bookRequest) {
        return new Book(
                id,
                bookRequest.getTitle(),
                bookRequest.getAuthor(),
                bookRequest.getCategory(),
                bookRequest.getRating()
        );
    }


}
