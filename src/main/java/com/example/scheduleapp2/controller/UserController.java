package com.example.scheduleapp2.controller;

import com.example.scheduleapp2.dto.CreateUserRequest;
import com.example.scheduleapp2.dto.CreateUserResponse;
import com.example.scheduleapp2.dto.GetUserResponse;
import com.example.scheduleapp2.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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
            @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    @GetMapping
    public RequestEntity<GetUserResponse> findAllUser() {
        List<GetUserResponse> result = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
