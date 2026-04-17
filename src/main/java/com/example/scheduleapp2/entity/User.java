package com.example.scheduleapp2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;

    // 생성자
    protected User() {}

    public User(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    // 기능
    public Long getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
}
