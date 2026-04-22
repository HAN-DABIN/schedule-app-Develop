package com.example.scheduleapp2.user.dto.user;

import java.time.LocalDateTime;
import java.util.List;

public class GetUserListResponse {
    // 속성
    private List<GetUserResponse> userList;

    // 생성자
    public GetUserListResponse(List<GetUserResponse> userList) {
        this.userList = userList;
    }

    // 기능
    public List<GetUserResponse> getUserList() {
        return userList;
    }

    public static class GetUserResponse {
        // 속성
        private final Long id;
        private final String userName;
        private final String email;
        private final LocalDateTime createdAt;
        private final LocalDateTime modifiedAt;

        // 생성자
        public GetUserResponse(Long id, String userName, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
            this.id = id;
            this.userName = userName;
            this.email = email;
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
        }

        // 기능
        public Long getId() {
            return id;
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
}
