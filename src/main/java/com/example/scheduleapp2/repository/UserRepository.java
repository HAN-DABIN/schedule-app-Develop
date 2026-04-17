package com.example.scheduleapp2.repository;

import com.example.scheduleapp2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
