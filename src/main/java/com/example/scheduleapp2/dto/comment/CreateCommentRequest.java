package com.example.scheduleapp2.dto.comment;

import com.example.scheduleapp2.entity.Schedule;

public class CreateCommentRequest {
    private String contents;
    private Long scheduleId;
    private Long userId;

    public String getContents() {
        return contents;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public Long getUserId() {
        return userId;
    }
}
