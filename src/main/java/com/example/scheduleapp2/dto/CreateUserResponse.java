package com.example.scheduleapp2.dto;

import java.time.LocalDateTime;

public class CreateUserResponse {
    // 속성
    private final Long userId;
    private final String userName;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    // 생성자
    public CreateUserResponse(Long userId, String userName, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    // 기능
    public Long getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
