package com.example.scheduleapp2.comment.dto;

import java.time.LocalDateTime;

public class CreateCommentResponse {
    // 속성
    private final Long id;
    private final String contents;
    private final Long userId;
    private final Long scheduleId;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    // 생성자
    public CreateCommentResponse(Long id, String contents, Long userId, Long scheduleId, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.contents = contents;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }

    // 기능
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
