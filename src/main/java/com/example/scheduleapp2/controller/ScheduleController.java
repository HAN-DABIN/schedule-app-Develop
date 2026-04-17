package com.example.scheduleapp2.controller;

import com.example.scheduleapp2.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.scheduleapp2.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    // 속성
    private final ScheduleService ScheduleService;

    // 생성자
    public ScheduleController(ScheduleService service) {
        this.ScheduleService = service;
    }

    // 기능
    @PostMapping
    public ResponseEntity<CreateScheduleResponse> CreateSchedule(
            @RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ScheduleService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<GetScheduleResponse>> findAllSchedule() {
        List<GetScheduleResponse> result = ScheduleService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> findOneSchedule(
            @PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(ScheduleService.findOne(scheduleId));
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> UpdateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(ScheduleService.update(scheduleId, request));
    }
}
