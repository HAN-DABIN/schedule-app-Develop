package com.example.scheduleapp2.comment.dto;

import java.time.LocalDateTime;
import java.util.List;

public class GetCommentListResponse {
    // 속성
    private List<GetCommentResponse> commentList;

    // 생성자
    public GetCommentListResponse(List<GetCommentResponse> commentList) {
        this.commentList = commentList;
    }

    // 기능
    public List<GetCommentResponse> getCommentList() {
        return commentList;
    }

    // 내부 DTO (중첩DTO)
    public static class GetCommentResponse {
        private final Long id;
        private final String contents;
        private final Long userId;
        private final Long scheduleId;
        private final LocalDateTime createAt;
        private final LocalDateTime modifiedAt;

        public GetCommentResponse(Long id, String contents, Long userId, Long scheduleId, LocalDateTime createAt, LocalDateTime modifiedAt) {
            this.id = id;
            this.contents = contents;
            this.userId = userId;
            this.scheduleId = scheduleId;
            this.createAt = createAt;
            this.modifiedAt = modifiedAt;
        }

        public Long getId() {
            return id;
        }

        public String getContents() {
            return contents;
        }

        public Long getUserId() {
            return userId;
        }

        public Long getScheduleId() {
            return scheduleId;
        }

        public LocalDateTime getCreateAt() {
            return createAt;
        }

        public LocalDateTime getModifiedAt() {
            return modifiedAt;
        }
    }
}
