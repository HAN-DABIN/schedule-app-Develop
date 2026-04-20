package com.example.scheduleapp2.exception;

import org.springframework.http.HttpStatus;

public class LoginUnauthorizedException extends ServiceException{
    public LoginUnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
