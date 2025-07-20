package com.learn.books.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookErrorResponse {

    private int status;

    private String message;


    private long timeStamp;

    public BookErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
