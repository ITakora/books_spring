package com.learn.books.controller;


import com.learn.books.entity.Book;
import com.learn.books.request.BookRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> getBooks (@RequestParam(required = false) String category) {

        if (category == null) {
            return books;
        }

       return books.stream().filter(book -> book.getCategory().equalsIgnoreCase(category)).toList();
    }



    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Book getBookById (@PathVariable @Min(value = 1) long id) {
//        for (Book book : books) {
//            if (book.getId() == id) {
//                return book;
//            }
//        }
//        return null;


        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);


    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createBook(@Valid @RequestBody BookRequest bookRequest) {
        long id = books.isEmpty() ?  1 :  books.get(books.size()-1).getId() +1;
        

        Book book = convertToBook(id, bookRequest);

        books.add(book);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateBook(@PathVariable @Min(value = 1) long id,@Valid @RequestBody BookRequest bookRequest) {
        for(int i=0; i<books.size(); i++) {
            if(books.get(i).getId() == id) {
                Book newBook = convertToBook(id, bookRequest);
                books.set(i, newBook);
                return;
            }
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable @Min(value = 1) long id) {
        books.removeIf(book -> book.getId() == id);
    }


    private Book convertToBook ( @Min(value = 1) long id,  BookRequest bookRequest) {
        return new Book(
                id,
                bookRequest.getTitle(),
                bookRequest.getAuthor(),
                bookRequest.getCategory(),
                bookRequest.getRating()
        );
    }


}
