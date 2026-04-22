package com.example.scheduleapp2.schedule.dto;

import java.time.LocalDateTime;
import java.util.List;

public class GetScheduleListResponse {
    // 속성
    private List<GetScheduleResponse> memberList;

    // 생성자
    public GetScheduleListResponse(List<GetScheduleResponse> memberList) {
        this.memberList = memberList;
    }

    // 기능
    public List<GetScheduleResponse> getMemberList() {
        return memberList;
    }


    public static class GetScheduleResponse {
        // 속성
        private Long id;
        private Long userId;
        private String userName;
        private String title;
        private String contents;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;


        // 생성자
        public GetScheduleResponse(Long id, Long userId, String userName, String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
            this.id = id;
            this.userId = userId;
            this.userName = userName;
            this.title = title;
            this.contents = contents;
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
        }

        // 기능

        public Long getId() {
            return id;
        }

        public Long getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public String getTitle() {
            return title;
        }

        public String getContents() {
            return contents;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public LocalDateTime getModifiedAt() {
            return modifiedAt;
        }
    }
}