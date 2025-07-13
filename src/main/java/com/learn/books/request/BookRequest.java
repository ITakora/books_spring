package com.learn.books.request;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {

    @Size(min = 1, max = 30)
    private String title;

    @Size(min = 1, max = 30)
    private String author;

    @Size(min = 1, max = 30)
    private String category;

    @Min(value = 1)
    @Max(value = 5)
    private int rating;

    public BookRequest(String title, String author, String category, int rating) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rating = rating;
    }


}
