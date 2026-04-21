package com.example.scheduleapp2.repository;

import com.example.scheduleapp2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
