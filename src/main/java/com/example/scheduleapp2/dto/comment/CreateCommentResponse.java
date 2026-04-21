package com.example.scheduleapp2.dto.comment;

import com.example.scheduleapp2.entity.Schedule;
import com.example.scheduleapp2.entity.User;

import java.time.LocalDateTime;

public class CreateCommentResponse {
    private final Long id;
    private final String contents;
    private final Long scheduleId;
    private final Long userId;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public CreateCommentResponse(Long id, String contents, Long scheduleId, Long userId, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.contents = contents;
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }

    public Long getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
