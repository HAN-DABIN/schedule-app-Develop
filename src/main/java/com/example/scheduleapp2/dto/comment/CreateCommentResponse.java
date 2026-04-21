package com.example.scheduleapp2.dto.comment;

import com.example.scheduleapp2.entity.Schedule;
import com.example.scheduleapp2.entity.User;

import java.time.LocalDateTime;

public class CreateCommentResponse {
    private final Long id;
    private final String contents;
    private final Long userId;
    private final Long scheduleId;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public CreateCommentResponse(Long id, String contents, Long userId, Long scheduleId, LocalDateTime createAt, LocalDateTime modifiedAt) {
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
