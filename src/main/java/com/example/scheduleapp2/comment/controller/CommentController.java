package com.example.scheduleapp2.comment.controller;

import com.example.scheduleapp2.comment.dto.CreateCommentRequest;
import com.example.scheduleapp2.comment.dto.CreateCommentResponse;
import com.example.scheduleapp2.comment.dto.GetCommentListResponse;
import com.example.scheduleapp2.comment.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    // 댓글 생성 API
    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(
            @Valid // 예외처리
            @RequestBody CreateCommentRequest request) { // 클라이언트가 보낸 데이터를 객체로 변환
        // service로 요청 전달 후 댓글 저장
        // 저장된 데이터를 201 Created 상태코드와 함께 응답 DTO 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(request));
    }

    // 댓글 전체 조회 API
    @GetMapping
    public ResponseEntity<GetCommentListResponse> getComment(){
        // service로 요청 전달 후 전체 댓글 조회
        // 댓글 전체 조회 후, 200 OK 상태코드와 함꼐 응답 DTO 반환
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findAll());
    }
}
