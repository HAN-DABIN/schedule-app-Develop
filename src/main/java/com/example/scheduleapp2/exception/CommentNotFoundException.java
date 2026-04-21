package com.example.scheduleapp2.exception;

import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends ServiceException {
    public CommentNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
