package com.example.scheduleapp2.user.service;
import com.example.scheduleapp2.config.PasswordEncoder;
import com.example.scheduleapp2.user.dto.login.LoginRequest;
import com.example.scheduleapp2.user.dto.login.SessionUser;
import com.example.scheduleapp2.user.entity.User;
import com.example.scheduleapp2.exception.LoginUnauthorizedException;
import com.example.scheduleapp2.exception.UserNotFoundException;
import com.example.scheduleapp2.user.repository.UserRepository;
import com.example.scheduleapp2.user.dto.user.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// Controller로부터 요청을 받아 실제 비즈니스 로직을 처리한다.
// DB 직접 접근은 Repository가 하고,
// Service는 흐름 제어 + 검증 + 예외처리 + DTO 변환 담당
@Service
public class UserService {
    // 속성
    private final UserRepository userRepository; // 유저 테이블 접근 담당 repository
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화 / 검증 객체

    // 생성자
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 유저 생성
    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        // 비밀번호 암호화
        String encodePassword = passwordEncoder.encode(request.getPassword());
        // 회원 엔티티 생성
        User user = new User(request.getUserName(), request.getEmail(), encodePassword);
        // DB 저장
        User saveUser = userRepository.save(user);
        // 응답 DTO 반환
        return new CreateUserResponse(
                saveUser.getId(),
                saveUser.getUserName(),
                saveUser.getEmail(),
                saveUser.getCreatedAt(),
                saveUser.getModifiedAt()
        );
    }

    // 유저 전체 조회
    @Transactional(readOnly = true)
    public GetUserListResponse findAll() {
        // 유저를 List에 담에 전체 조회
        List<User> userList = userRepository.findAll();
        // entity -> dto 변환
        List<GetUserListResponse.UserDto> userResponses = userList.stream()
                .map(user -> new GetUserListResponse.UserDto(
                        user.getId(),
                        user.getUserName(),
                        user.getEmail(),
                        user.getCreatedAt(),
                        user.getModifiedAt()
                ))
                .collect(Collectors.toList());
        // 리스트 응답 dto 반환
        return new GetUserListResponse(userResponses);
    }

    // 유저 단 건 조회
    @Transactional(readOnly = true)
    public GetOneUserResponse findOne(Long userId) {
        // userId로 회원 조회
        User user = userRepository.findById(userId)
                // 없으면 예외 발생
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 없습니다.")
        );
        // 조회 성공 시 dto 반환
        return new GetOneUserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 유저 수정
    @Transactional
    public UpdateUserResponse update(Long userId, UpdateUserRequest request) {
        // 비밀번호 암호화
        String encodePassword = passwordEncoder.encode(request.getPassword());
        // 수정할 유저 userId로 조회
        User user = userRepository.findById(userId)
                // 없으면 예외 발생
                .orElseThrow(() -> new UserNotFoundException("수정할 유저가 없습니다.")
        );
        // entity 값 변경
        user.UpdateUser(
                request.getUserName(),
                request.getEmail(),
                encodePassword
        );
        // 응답 dto 반환
        return new UpdateUserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 유저 삭제
    @Transactional
    public void delete(Long userId) {
        // userId로 유저 존재 여부 확인
        boolean existence = userRepository.existsById(userId);
        // 없으면 예외처리
        if(!existence) {
            throw new UserNotFoundException("삭제할 유저가 없습니다.");
            // 있으면 삭제
        } userRepository.deleteById(userId);
    }

    // 로그인
    @Transactional(readOnly = true)
    public SessionUser login(
            @Valid LoginRequest request) { // 예외 처리
        // userEmail로 회원 조회
        User user = userRepository.findByEmail(request.getEmail())
                // 없으면 예외 발생
                .orElseThrow(() -> new LoginUnauthorizedException("존재하지 않는 email입니다.")
        );
        // 입력된 비밀번호와 저장(암호화)된 비밀번호가 같은지 확인
        boolean isMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!isMatch) {
            // 아니면 예외 발생
            throw new LoginUnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        // 맞으면 dto 반환
        return new SessionUser(
                user.getId(),
                user.getEmail()
        );
    }
}
