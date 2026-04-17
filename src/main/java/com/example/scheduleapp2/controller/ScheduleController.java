package com.example.scheduleapp2.controller;

import com.example.scheduleapp2.dto.CreateScheduleRequest;
import com.example.scheduleapp2.dto.CreateScheduleResponse;
import com.example.scheduleapp2.dto.GetScheduleResponse;
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

    @GetMapping("/{id)")
    public ResponseEntity<GetScheduleResponse> findOneSchedule(
            @PathVariable Long scheduleId) { // service에 id넘기기
        return ResponseEntity.status(HttpStatus.OK).body(ScheduleService.findOne(scheduleId));

}
