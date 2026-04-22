package com.example.scheduleapp2.schedule.service;

import com.example.scheduleapp2.schedule.entity.Schedule;
import com.example.scheduleapp2.user.entity.User;
import com.example.scheduleapp2.exception.ScheduleNotFoundException;
import com.example.scheduleapp2.user.repository.UserRepository;
import com.example.scheduleapp2.schedule.dto.*;
import org.springframework.stereotype.Service;
import com.example.scheduleapp2.schedule.repository.ScheduleRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Controller로부터 요청을 받아 실제 비즈니스 로직을 처리한다.
// DB 직접 접근은 Repository가 하고,
// Service는 흐름 제어 + 검증 + 예외처리 + DTO 변환 담당
@Service
public class ScheduleService {

    // 속성
    private final ScheduleRepository scheduleRepository; // 일정 entity 접근 속성
    private final UserRepository userRepository; // 유저 entity 접근 속성

    // 생성자
    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    // 일정 생성
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        // request에 있는 userId로 유저 조회
        User user = userRepository.findById(request.getUserId())
                // 없으면 예외 발생
                .orElseThrow(() -> new ScheduleNotFoundException("해당 유저가 없습니다."));
        // 일정 엔티티 생성
        Schedule schedule = new Schedule(user, request.getTitle(), request.getContents());
        // DB 저장
        Schedule savedSchedule = scheduleRepository.save(schedule);
        // 응답 DTO 반환
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getUser().getId(),
                savedSchedule.getUser().getUserName(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    // 일정 전체 조회
    @Transactional(readOnly = true)
    public GetScheduleListResponse findAll() {
        // entity -> dto 리스트 변환
        List<Schedule> scheduleList = scheduleRepository.findAll();
        List<GetScheduleListResponse.ScheduleDto> scheduleResponses = scheduleList.stream()
                .map(schedule -> new GetScheduleListResponse.ScheduleDto(
                        schedule.getId(),
                        schedule.getUser().getId(),
                        schedule.getUser().getUserName(),
                        schedule.getTitle(),
                        schedule.getContents(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                ))
                .collect(Collectors.toList());
        // 응답 리스트 dto 반환
        return new GetScheduleListResponse(scheduleResponses);
    }


    // 일정 단 건 조회
    @Transactional(readOnly = true)
    public GetOneScheduleResponse findOne(Long scheduleId) {
        // 전달받은 scheduleId로 일정 조회
        // 값이 있으면 Schedule 객체 반환
            Schedule schedule = scheduleRepository.findById(scheduleId)
                    // 없으면 예외 발생
                    .orElseThrow(() -> new ScheduleNotFoundException("해당 일정을 찾을 수 없습니다.")
            );
            // 단 건 조회 DTO
            return new GetOneScheduleResponse(
                    schedule.getId(),
                    schedule.getUser().getId(),
                    schedule.getUser().getUserName(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
    }

    // 일정 수정
    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        // 요청한 유저 조회
        User user = userRepository.findById(request.getUserId())
                // 없으면 예외 발생
                .orElseThrow(() -> new IllegalStateException("해당 유저가 없습니다."));
        // 수정 일정 조회
        Schedule schedule = scheduleRepository.findById(scheduleId)
                // 없으면 예외 발생
                .orElseThrow(() -> new ScheduleNotFoundException("수정할 일정이 없습니다.")
        );
        // 엔티티 값 변경
        schedule.UpdateSchedule(
                user,
                request.getTitle(),
                request.getContents());
        // 응답 DTO 반환
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getUser().getId(),
                schedule.getUser().getUserName(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 일정 삭제
    @Transactional
    public void delete(Long scheduleId) {
        // 일정 존재 여부 확인
        boolean existence = scheduleRepository.existsById(scheduleId);
        // 없으면 예외처리
        if (!existence) {
            throw new ScheduleNotFoundException("삭제할 일정이 없습니다.");
        }
        // 존재하면 삭제
        scheduleRepository.deleteById(scheduleId);
    }
}

