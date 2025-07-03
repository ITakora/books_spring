package com.learn.books.request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {

    private String title;

    private String author;

    private String category;

    private int rating;

    public BookRequest(String title, String author, String category, int rating) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rating = rating;
    }


}
