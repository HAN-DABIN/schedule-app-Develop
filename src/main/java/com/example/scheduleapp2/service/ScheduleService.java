package com.example.scheduleapp2.service;

import com.example.scheduleapp2.dto.CreateScheduleRequest;
import com.example.scheduleapp2.dto.CreateScheduleResponse;
import com.example.scheduleapp2.entity.Schedule;
import org.springframework.stereotype.Service;
import com.example.scheduleapp2.repository.ScheduleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleService {

    // 속성
    private final ScheduleRepository scheduleRepository;

    // 생성자
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(request.getUserId(), request.getTitle(), request.getContents());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getUserId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }
}
