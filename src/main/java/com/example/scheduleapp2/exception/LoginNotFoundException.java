package com.example.scheduleapp2.exception;

import org.springframework.http.HttpStatus;

public class LoginNotFoundException extends ServiceException{
    public LoginNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
