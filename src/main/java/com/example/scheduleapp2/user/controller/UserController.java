package com.example.scheduleapp2.user.controller;

import com.example.scheduleapp2.user.dto.login.LoginRequest;
import com.example.scheduleapp2.user.dto.login.LoginResponse;
import com.example.scheduleapp2.user.dto.login.SessionUser;
import com.example.scheduleapp2.user.service.UserService;
import com.example.scheduleapp2.user.dto.user.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    // 속성
    private final UserService userService; // 비즈니스 로직 처리 담당 Service

    // 생성자
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 기능
    // 유저 생성 API
    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(
            @Valid // 예외처리
            @RequestBody CreateUserRequest request) { // 클라이언트가 보낸 데이터를 객체로 변환
        // service로 요청 전달 후 유저 저장
        // 저장된 데이터를 201 Created 상태코드와 함께 응답 DTO 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid // 예외 처리 (검증)
            // 클라이언트가 보낸 JSON 요청 데이터를 LoginRequest 객체로 변환
            @RequestBody LoginRequest request, HttpSession session) {
        // service에서 검증 후 SessionUser 반환
        SessionUser sessionUser = userService.login(request);
        // 세션에 로그인 사용자 저장 -> 로그인 상태 기억
        session.setAttribute("loginUser", sessionUser);
        // 응답 DTO 생성
        LoginResponse response = new LoginResponse(sessionUser.getId(), sessionUser.getEmail());
        // 로그인 성공 응답 반환
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 유저 전체 조회 API
    @GetMapping
    public ResponseEntity<GetUserListResponse> findAllUser() {
        // service로 요청 전달 후 전체 댓글 조회
        // 유저 전체 조회 후, 200 OK 상태코드와 함꼐 응답 DTO 반환
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    // 유저 단 건 조회 API
    @GetMapping("/{userId}") // ID값으로 특정 일정 조회
    public ResponseEntity<GetOneUserResponse> findOneUser(
            @PathVariable Long userId) { // 조회할 일정 Id
        // service에서 userId에 해당하는 유저 조회 후 200 OK 상태코드와 함꼐 응답 DTO 반환
        return ResponseEntity.status(HttpStatus.OK).body(userService.findOne(userId));
    }

    // 유저 수정 API
    @PutMapping("/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @Valid // 예외 처리
            @PathVariable Long userId, // 수정할 유저 Id
            @RequestBody UpdateUserRequest request) { // 수정할 내용을 json으로 전달받아 DTO 변환
        // service에서 해당 유저 존재 여부 확인 후 수정 진행, 200 OK 상태코드와 함꼐 응답 DTO 반환
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userId, request));
    }

    // 유저 삭제 API
    @DeleteMapping("/{userId}") // ID값으로 특정 일정 삭제
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long userId) {
        // service에서 삭제 수행
        userService.delete(userId);
        // 성공 시 204 NO_Content 상태코드, 응답 body없음
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
