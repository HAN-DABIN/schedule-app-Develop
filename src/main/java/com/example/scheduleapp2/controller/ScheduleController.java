package com.example.scheduleapp2.controller;

import com.example.scheduleapp2.dto.CreateScheduleRequest;
import com.example.scheduleapp2.dto.CreateScheduleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.scheduleapp2.service.ScheduleService;

@RestController
@RequestMapping
public class ScheduleController {

    // 속성
    private final ScheduleService ScheduleService;

    // 생성자
    public ScheduleController(ScheduleService service) {
        this.ScheduleService = service;
    }

    // 기능
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> CreateSchedule(
            @RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ScheduleService.save(request));
    }
}
