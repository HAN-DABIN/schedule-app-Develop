package com.example.scheduleapp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ScheduleApp2Application {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleApp2Application.class, args);
    }

}
