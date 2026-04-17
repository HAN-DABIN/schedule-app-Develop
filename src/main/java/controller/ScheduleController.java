package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ScheduleService;

@RestController
@RequestMapping
public class ScheduleController {

    // 속성
    private final ScheduleService service;

    // 생성자
    public ScheduleController(ScheduleService service) {
        this.service = service;
    }
}
