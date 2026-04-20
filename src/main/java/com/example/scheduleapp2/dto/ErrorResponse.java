package com.example.scheduleapp2.dto;

public class ErrorResponse {
private String message;

    public ErrorResponse(String message) {
       this.message = message;
    }

    public String getMessage() { return message; }
}
