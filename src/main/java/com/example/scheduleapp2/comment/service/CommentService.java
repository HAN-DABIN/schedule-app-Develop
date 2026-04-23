package com.example.scheduleapp2.comment.service;

import com.example.scheduleapp2.comment.dto.CreateCommentRequest;
import com.example.scheduleapp2.comment.dto.CreateCommentResponse;
import com.example.scheduleapp2.comment.dto.GetCommentListResponse;
import com.example.scheduleapp2.comment.entity.Comment;
import com.example.scheduleapp2.exception.CommentNotFoundException;
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
    // 댓글 생성 기능
    @Transactional
    public CreateCommentResponse save(CreateCommentRequest request) {
        // 요청받은 userId로 데이터 조회
        User user = userRepository.findById(request.getUserId())
                // 없으면 예외 발생
                .orElseThrow(() -> new CommentNotFoundException("해당 유저가 없습니다."));
        // 요청받은 scheduleId로 데이터 조회
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                // 없으면 예외 발생
                .orElseThrow(() -> new CommentNotFoundException("해당 일정이 없습니다."));
        // 댓글 엔티티 생성
        Comment comment = new Comment(request.getContents(), user, schedule);
        // DB 저장
        Comment savedComment = commentRepository.save(comment);
        // 엔티티를 응답 DTO로 반환
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
    public GetCommentListResponse findAll(){
        // 전체 댓글 조회
        List<Comment> commentsList = commentRepository.findAll();
        // stream을 이용해 Comment List를 응답 DTO List로 변경
        List<GetCommentListResponse.GetCommentResponse> commentResponses = commentsList.stream()
                .map(comment -> new GetCommentListResponse.GetCommentResponse(
                        comment.getId(),
                        comment.getContents(),
                        comment.getUser().getId(),
                        comment.getSchedule().getId(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()
                ))
                .collect(Collectors.toList());
        // 리스트 응답 DTO 반환
        return new GetCommentListResponse(commentResponses);
    }
}
