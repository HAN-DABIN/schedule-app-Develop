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

@Service
public class ScheduleService {

    // 속성
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 생성자
    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ScheduleNotFoundException("해당 유저가 없습니다."));
        Schedule schedule = new Schedule(user, request.getTitle(), request.getContents());
        Schedule savedSchedule = scheduleRepository.save(schedule);
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
    @Transactional(readOnly = true)
    public GetScheduleListResponse findAll() {
        List<Schedule> scheduleList = scheduleRepository.findAll();
        List<GetScheduleListResponse.GetScheduleResponse> scheduleResponses = scheduleList.stream()
                .map(schedule -> new GetScheduleListResponse.GetScheduleResponse(
                        schedule.getId(),
                        schedule.getUser().getId(),
                        schedule.getUser().getUserName(),
                        schedule.getTitle(),
                        schedule.getContents(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                ))
                .collect(Collectors.toList());
        return new GetScheduleListResponse(scheduleResponses);
    }


    @Transactional(readOnly = true)
    public GetScheduleListResponse.GetScheduleResponse findOne(Long scheduleId) {
            Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                    () -> new ScheduleNotFoundException("해당 일정을 찾을 수 없습니다.")
            );
            return new GetScheduleListResponse.GetScheduleResponse(
                    schedule.getId(),
                    schedule.getUser().getId(),
                    schedule.getUser().getUserName(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
    }

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalStateException("해당 유저가 없습니다."));
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("수정할 일정이 없습니다.")
        );
        schedule.UpdateSchedule(
                user,
                request.getTitle(),
                request.getContents());
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

    @Transactional
    public void delete(Long scheduleId) {
        boolean existence = scheduleRepository.existsById(scheduleId);
        if (!existence) {
            throw new ScheduleNotFoundException("삭제할 일정이 없습니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}

