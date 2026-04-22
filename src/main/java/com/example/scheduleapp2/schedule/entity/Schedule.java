package com.example.scheduleapp2.schedule.entity;

import com.example.scheduleapp2.entity.BaseEntity;
import com.example.scheduleapp2.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity {

    // 속성
    @Id // 일정 고유 식별자 (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 연관관계 (N:1)
    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩
    @JoinColumn(name = "user_id") // FK 컬럼명 지정
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
