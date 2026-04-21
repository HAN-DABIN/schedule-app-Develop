package com.example.scheduleapp2.comment.service;

import com.example.scheduleapp2.comment.dto.CreateCommentRequest;
import com.example.scheduleapp2.comment.dto.CreateCommentResponse;
import com.example.scheduleapp2.comment.dto.GetCommentResponse;
import com.example.scheduleapp2.comment.entity.Comment;
import com.example.scheduleapp2.schedule.entity.Schedule;
import com.example.scheduleapp2.user.entity.User;
import com.example.scheduleapp2.comment.repository.CommentRepository;
import com.example.scheduleapp2.schedule.repository.ScheduleRepository;
import com.example.scheduleapp2.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<GetCommentResponse> findAll() {
        return commentRepository.findAll()
                .stream()
                .map(comment -> new GetCommentResponse(
                        comment.getId(),
                        comment.getContents(),
                        comment.getUser().getId(),
                        comment.getSchedule().getId(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()
                ))
                .collect(Collectors.toList());
    }
}
