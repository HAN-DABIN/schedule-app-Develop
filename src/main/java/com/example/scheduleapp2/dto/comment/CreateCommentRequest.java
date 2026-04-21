package com.example.scheduleapp2.dto.comment;

public class CreateCommentRequest {
    private String contents;
    private Long userId;
    private Long scheduleId;


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
