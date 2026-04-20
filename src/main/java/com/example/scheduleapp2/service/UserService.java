package com.example.scheduleapp2.service;
import com.example.scheduleapp2.dto.login.LoginRequest;
import com.example.scheduleapp2.dto.login.SessionUser;
import com.example.scheduleapp2.dto.user.*;
import com.example.scheduleapp2.entity.User;
import com.example.scheduleapp2.exception.LoginNotFoundException;
import com.example.scheduleapp2.exception.UserNotFoundException;
import com.example.scheduleapp2.repository.UserRepository;
import jakarta.validation.Valid;
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
        User user = new User(request.getUserName(), request.getEmail(), request.getPassword());
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

    @Transactional(readOnly = true)
    public GetUserResponse findOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("해당 유저가 없습니다.")
        );
        return new GetUserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public UpdateUserResponse update(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("수정할 유저가 없습니다.")
        );
        user.UpdateUser(
                request.getUserName(),
                request.getEmail(),
                request.getPassword()
        );
        return new UpdateUserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public void delete(Long userId) {
        boolean existence = userRepository.existsById(userId);
        if(!existence) {
            throw new UserNotFoundException("삭제할 유저가 없습니다.");
        } userRepository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    public SessionUser login(
            @Valid LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new LoginNotFoundException("존재하지 않는 email입니다.")
        );
        return new SessionUser(
                user.getId(),
                user.getEmail()
        );
    }
}
