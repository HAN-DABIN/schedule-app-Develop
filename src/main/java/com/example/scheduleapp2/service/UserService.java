package com.example.scheduleapp2.service;

import com.example.scheduleapp2.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    // 속성
    private final UserRepository userRepository;

    // 생성자
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // 기능
}
