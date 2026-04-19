package com.example.scheduleapp2.dto;

import jakarta.validation.constraints.Size;

public class UpdateUserRequest {
    // 속성
    private String userName;
    private String email;
    @Size(min = 8, message = "비밀번호는 {min}자 이상이어야 합니다")
    private String password;

    // 생성자

    // 기능

    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
