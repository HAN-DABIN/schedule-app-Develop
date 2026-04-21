package com.example.scheduleapp2.service;

import com.example.scheduleapp2.dto.comment.CreateCommentRequest;
import com.example.scheduleapp2.dto.comment.CreateCommentResponse;
import com.example.scheduleapp2.entity.Comment;
import com.example.scheduleapp2.entity.Schedule;
import com.example.scheduleapp2.entity.User;
import com.example.scheduleapp2.repository.CommentRepository;
import com.example.scheduleapp2.repository.ScheduleRepository;
import com.example.scheduleapp2.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    // 속성
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 생성자
    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    // 기능
    @Transactional
    public CreateCommentResponse save(CreateCommentRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalStateException("해당 유저가 없습니다."));
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new IllegalStateException("해당 일정이 없습니다."));
        Comment comment = new Comment(request.getContents(), user, schedule);
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getContents(),
                savedComment.getUser().getId(),
                savedComment.getSchedule().getId(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }
}
