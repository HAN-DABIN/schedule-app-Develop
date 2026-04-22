package com.example.scheduleapp2.user.dto.user;

import java.time.LocalDateTime;
import java.util.List;

// 다 건 조회 DTO
public class GetUserListResponse {
    // 속성
    private List<UserDto> userList;

    // 생성자
    public GetUserListResponse(List<UserDto> userList) {
        this.userList = userList;
    }

    // 기능
    public List<UserDto> getUserList() {
        return userList;
    }

    // 내부 DTO (중첩 DTO)
    public static class UserDto {
        // 속성
        private final Long id;
        private final String userName;
        private final String email;
        private final LocalDateTime createdAt;
        private final LocalDateTime modifiedAt;

        // 생성자
        public UserDto(Long id, String userName, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
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
