package com.example.scheduleapp2.service;

import org.springframework.stereotype.Service;
import com.example.scheduleapp2.repository.ScheduleRepository;

@Service
public class ScheduleService {

    // 속성
    private final ScheduleRepository scheduleRepository;

    // 생성자
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
}
