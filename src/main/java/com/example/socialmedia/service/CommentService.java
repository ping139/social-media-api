package com.example.socialmedia.service;

import com.example.socialmedia.model.Comment;
import com.example.socialmedia.vo.CreateCommentRequest;
import com.example.socialmedia.vo.Response;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final JdbcTemplate jdbcTemplate;

    public CommentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 新增留言
    public void createComment(CreateCommentRequest request) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp createdAt = Timestamp.valueOf(now);

        String sql = "CALL create_comment(?, ?, ?, ?)";
        jdbcTemplate.update(sql, request.getUserId(), request.getPostId(), request.getContent(), createdAt);
    }

    // 查詢某篇文章的留言
    public List<Comment> getCommentsByPostId(Long postId) {
        String sql = "CALL get_comments_by_post_id(?)";
        return jdbcTemplate.query(sql, new Object[]{postId}, (rs, rowNum) -> {
            Comment comment = new Comment();
            comment.setCommentId(rs.getLong("comment_id"));
            comment.setUserId(rs.getLong("user_id"));
            comment.setPostId(rs.getLong("post_id"));
            comment.setContent(rs.getString("content"));
            comment.setCreatedAt(rs.getTimestamp("created_at"));
            return comment;
        });
    }
}
