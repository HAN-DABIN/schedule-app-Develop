package com.example.scheduleapp2.schedule.controller;

import com.example.scheduleapp2.schedule.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.scheduleapp2.schedule.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    // 속성
    private final ScheduleService scheduleService; // 비즈니스 로직 처리 담당 Service

    // 생성자
    public ScheduleController(ScheduleService service) {
        this.scheduleService = service;
    }

    // 기능
    // 일정 생성  API
    @PostMapping
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @Valid // 예외처리
            @RequestBody CreateScheduleRequest request) { // 클라이언트가 보낸 데이터를 객체로 변환
        // service로 요청 전달 후 일정 저장
        // 저장된 데이터를 201 Created 상태코드와 함께 응답 DTO 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    // 일정 전체 조회 API
    @GetMapping
    public ResponseEntity<GetScheduleListResponse> findAllSchedule() {
        // service로 요청 전달 후 전체 댓글 조회
        // 일정 전체 조회 후, 200 OK 상태코드와 함꼐 응답 DTO 반환
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }

    // 일정 단 건 조회 API
    @GetMapping("/{scheduleId}") // ID값으로 특정 일정 조회
    public ResponseEntity<GetOneScheduleResponse> findOneSchedule(
            @PathVariable Long scheduleId) { // 조회할 일정 Id
        // service에서 scheduleId에 해당하는 일정 조회 후 200 OK 상태코드와 함꼐 응답 DTO 반환
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }

    // 일정 수정 API
    @PutMapping("/{scheduleId}") // ID값으로 특정 일정 수정
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @Valid // 예외 처리
            @PathVariable Long scheduleId, // 수정할 일정 Id
            @RequestBody UpdateScheduleRequest request) { // 수정할 내용을 json으로 전달받아 DTO 변환
        // service에서 해당 일정 존재 여부 확인 후 수정 진행, 200 OK 상태코드와 함꼐 응답 DTO 반환
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }

    // 일정 삭제 API
    @DeleteMapping("/{scheduleId}") // ID값으로 특정 일정 삭제
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId) { // 삭제할 일정 Id
        // service에서 삭제 수행
        scheduleService.delete(scheduleId);
        // 성공 시 204 NO_Content 상태코드, 응답 body없음
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
