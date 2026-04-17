package com.example.scheduleapp2.service;

import com.example.scheduleapp2.dto.CreateUserRequest;
import com.example.scheduleapp2.dto.CreateUserResponse;
import com.example.scheduleapp2.dto.GetUserResponse;
import com.example.scheduleapp2.entity.User;
import com.example.scheduleapp2.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<GetUserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> new GetUserResponse(
                        user.getId(),
                        user.getUserName(),
                        user.getEmail(),
                        user.getCreatedAt(),
                        user.getModifiedAt()
                ))
                .collect(Collectors.toList());
    }


    // 기능
}
