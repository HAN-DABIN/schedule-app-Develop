package com.example.scheduleapp2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{
    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contents;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;


    //생성자
    protected Comment() {}

    public Comment(String contents, User user, Schedule schedule) {
        this.contents = contents;
        this.user = user;
        this.schedule = schedule;
    }

    // 기능
    public Long getId() {
        return id;
    }
    public String getContents() {
        return contents;
    }
    public User getUser() {
        return user;
    }
    public Schedule getSchedule() {
        return schedule;
    }
}
