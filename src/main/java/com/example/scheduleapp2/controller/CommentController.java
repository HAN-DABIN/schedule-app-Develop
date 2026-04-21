package com.example.scheduleapp2.controller;

import com.example.scheduleapp2.dto.comment.CreateCommentRequest;
import com.example.scheduleapp2.dto.comment.CreateCommentResponse;
import com.example.scheduleapp2.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {
    // 속성
    private final CommentService commentService;

    // 생성자
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 기능
    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(
            @RequestBody CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(request));
    }

}
