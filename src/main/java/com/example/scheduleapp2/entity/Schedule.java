package com.example.scheduleapp2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity{

    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String title;
    private String contents;

    // 생성자
    protected Schedule() {}

    public Schedule(Long userId, String title, String contents) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
    }

    // 기능

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
