package com.example.scheduleapp2.dto.login;

import jakarta.validation.constraints.Size;

public class LoginRequest {
    // 속성
    private String email;
    @Size(min = 8, message = "비밀번호는 {min}자 이상 입력해주세요.")
    private String password;

    // 생성자

    // 기능
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
