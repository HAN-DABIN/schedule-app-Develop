package com.example.scheduleapp2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.scheduleapp2.service.ScheduleService;

@RestController
@RequestMapping
public class ScheduleController {

    // 속성
    private final ScheduleService Scheduleservice;

    // 생성자
    public ScheduleController(ScheduleService service) {
        this.Scheduleservice = service;
    }

    // 기능
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse>
}
