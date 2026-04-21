package com.example.scheduleapp2.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateScheduleRequest {

    // 속성
    @NotNull(message = "유저 ID는 필수 입력값입니다.")
    @Positive(message = "유저 ID는 1 이상의 양수여야 합니다.")
    private Long userId;
    @NotBlank(message = "일정 제목은 필수 입력값입니다.")
    @Size(max = 20, message = "일정 제목은 최대 {max}자까지 입력 가능합니다.")
    private String title;
    @NotBlank(message = "일정 내용은 필수 입력값입니다.")
    @Size(max = 50, message = "일정 내용은 최대 {max}자까지 입력 가능합니다.")
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
