package com.example.scheduleapp2.exception;

import org.springframework.http.HttpStatus;

// 일정 조회 실패 시 사용하는 커스텀 예외 클래스
public class ScheduleNotFoundException extends ServiceException{
    public ScheduleNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
