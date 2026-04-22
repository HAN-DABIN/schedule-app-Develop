package com.example.scheduleapp2.exception.exceptionDto;

// 응답 형태를 json 형태로 담아주기 위한 DTO
public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
       this.message = message;
    }

    public String getMessage() { return message; }
}
