package com.example.scheduleapp2.comment.entity;

import com.example.scheduleapp2.entity.BaseEntity;
import com.example.scheduleapp2.schedule.entity.Schedule;
import com.example.scheduleapp2.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity { // 생성일/수정일 공통 필드 상속
    // 속성
    @Id // 댓글 고유 식별자(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contents;
    // 연관관계 (N:1)
    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩
    @JoinColumn(name = "user_id") // FK 컬럼명 지정
    private User user;
    // 연관관계 (N:1)
    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩
    @JoinColumn(name = "schedule_id") // FK 컬럼명 지정
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
