package com.example.scheduleapp2.dto;

public class LoginResponse {
    // 속성
    private final String email;
    private final String password;

    // 생성자
    public LoginResponse(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // 기능
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
