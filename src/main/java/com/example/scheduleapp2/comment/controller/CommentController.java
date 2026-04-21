package com.example.scheduleapp2.comment.controller;

import com.example.scheduleapp2.comment.dto.CreateCommentRequest;
import com.example.scheduleapp2.comment.dto.CreateCommentResponse;
import com.example.scheduleapp2.comment.dto.GetCommentResponse;
import com.example.scheduleapp2.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<GetCommentResponse>> getComment(){
        List<GetCommentResponse> result = commentService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
