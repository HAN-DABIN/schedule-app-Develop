package com.example.scheduleapp2.dto.login;

public class LoginResponse {
    // 속성
    private final Long id;
    private final String email;

    // 생성자
    public LoginResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    // 기능

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
