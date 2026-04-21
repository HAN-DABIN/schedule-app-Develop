package com.example.scheduleapp2.comment.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateCommentRequest {
    @NotBlank(message = "댓글 내용을 입력해주세요.")
    @Size(max = 20, message = "댓글은 최대 20자까지 입력 가능합니다.")
    private String contents;
    @NotNull(message = "유저 ID는 필수 입력값입니다.")
    @Positive(message = "유저 ID는 1 이상의 양수여야 합니다.")
    private Long userId;
    @NotNull(message = "일정 ID는 필수 입력값입니다.")
    @Positive(message = "일정 ID는 1 이상의 양수여야 합니다.")
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
