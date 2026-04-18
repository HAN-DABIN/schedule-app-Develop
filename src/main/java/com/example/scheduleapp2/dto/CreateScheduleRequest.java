package com.example.scheduleapp2.dto;

public class CreateScheduleRequest {

    // 속성
    private Long userId;
    private String title;
    private String contents;

    // 생성자

    // 기능

    public Long getUserId() {
        return userId;
    }
    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
