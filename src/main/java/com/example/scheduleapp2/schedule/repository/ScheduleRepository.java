package com.example.scheduleapp2.schedule.repository;

import com.example.scheduleapp2.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
