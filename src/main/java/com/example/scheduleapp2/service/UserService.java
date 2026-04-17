package com.example.scheduleapp2.service;

import com.example.scheduleapp2.dto.CreateUserRequest;
import com.example.scheduleapp2.dto.CreateUserResponse;
import com.example.scheduleapp2.entity.User;
import com.example.scheduleapp2.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    // 속성
    private final UserRepository userRepository;

    // 생성자
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        User user = new User(request.getUserName(), request.getEmail());
        User saveUser = userRepository.save(user);
        return new CreateUserResponse(
                saveUser.getId(),
                saveUser.getUserName(),
                saveUser.getEmail(),
                saveUser.getCreatedAt(),
                saveUser.getModifiedAt()
        );
    }


    // 기능
}
