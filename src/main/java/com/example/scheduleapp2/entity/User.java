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
    private String password;

    // 생성자
    protected User() {}

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void UpdateUser(String userName, String email, String password) {
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
    public String getPassword() {
        return password;
    }
}
