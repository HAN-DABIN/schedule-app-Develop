package com.example.scheduleapp2.repository;

import com.example.scheduleapp2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
