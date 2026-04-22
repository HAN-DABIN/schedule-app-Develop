package com.example.scheduleapp2.schedule.entity;

import com.example.scheduleapp2.entity.BaseEntity;
import com.example.scheduleapp2.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity {

    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String title;
    private String contents;

    // 생성자
    protected Schedule() {}

    public Schedule(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    public void UpdateSchedule(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    // 기능

    public Long getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public String getTitle() {
        return title;
    }
    public String getContents() {
        return contents;
    }
}
