package com.example.scheduleapp2.service;
import com.example.scheduleapp2.config.PasswordEncoder;
import com.example.scheduleapp2.dto.login.LoginRequest;
import com.example.scheduleapp2.dto.login.SessionUser;
import com.example.scheduleapp2.dto.user.*;
import com.example.scheduleapp2.entity.User;
import com.example.scheduleapp2.exception.LoginUnauthorizedException;
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
    private final PasswordEncoder passwordEncoder;

    // 생성자
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        String encodePassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getUserName(), request.getEmail(), encodePassword);
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
        String encodePassword = passwordEncoder.encode(request.getPassword());
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("수정할 유저가 없습니다.")
        );
        user.UpdateUser(
                request.getUserName(),
                request.getEmail(),
                encodePassword
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
                () -> new LoginUnauthorizedException("존재하지 않는 email입니다.")
        );
        boolean isMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!isMatch) {
            throw new LoginUnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return new SessionUser(
                user.getId(),
                user.getEmail()
        );
    }
}
