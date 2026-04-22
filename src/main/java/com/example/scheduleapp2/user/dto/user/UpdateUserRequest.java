package com.example.scheduleapp2.user.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateUserRequest {
    // 속성
    @NotBlank(message = "유저명은 필수 입력값입니다.") // 빈칸 금지
    // 2~10자
    @Size(min = 2, max = 10, message = "유저명은 최소 {min}자부터 최대 {max}자까지 입력 가능합니다.")
    private String userName;
    @NotBlank(message = "이메일은 필수 입력값입니다.") // 빈칸 금지
    @Email(message = "올바른 이메일 형식이 아닙니다.") // 이메일 형식 (aaa@aaa.com)
    private String email;
    @NotBlank(message = "비밀번호는 필수 입력값입니다.") // 빈칸 금지
    // 영문숫자포함 8자
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
            message = "비밀번호는 영문자와 숫자를 포함한 8자 이상이어야 합니다."
    )
    private String password;

    // 생성자

    // 기능
    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
