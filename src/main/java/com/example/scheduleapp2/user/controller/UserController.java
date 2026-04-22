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
    private final UserService userService;

    // 생성자
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 기능
    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(
            @Valid
            @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid
            @RequestBody LoginRequest request, HttpSession session) {
        SessionUser sessionUser = userService.login(request);
        session.setAttribute("loginUser", sessionUser);
        LoginResponse response = new LoginResponse(sessionUser.getId(), sessionUser.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<GetUserListResponse> findAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetOneUserResponse> findOneUser(
            @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findOne(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @Valid
            @PathVariable Long userId,
            @RequestBody UpdateUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
