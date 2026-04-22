package com.example.scheduleapp2.exception;

import org.springframework.http.HttpStatus;

// 로그인 실패 시 사용하는 커스텀 예외 클래스
public class LoginUnauthorizedException extends ServiceException{
    public LoginUnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
