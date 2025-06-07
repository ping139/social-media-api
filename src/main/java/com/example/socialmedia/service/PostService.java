package com.example.socialmedia.service;

import java.sql.CallableStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.socialmedia.model.Post;
import com.example.socialmedia.vo.CreatePostRequest;
import com.example.socialmedia.vo.Response;
import com.example.socialmedia.vo.UpdatePostRequest;

@Service
public class PostService {

    private final JdbcTemplate jdbcTemplate;

    public PostService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Response createPost(CreatePostRequest request) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp createdAt = Timestamp.valueOf(now);

        Long postId = jdbcTemplate.execute(
            (CallableStatementCreator) con -> {
                CallableStatement cs = con.prepareCall("{CALL create_post(?, ?, ?, ?, ?)}");
                cs.setLong(1, request.getUserId());
                cs.setString(2, request.getContent());
                cs.setString(3, request.getImage());
                cs.setTimestamp(4, createdAt);
                cs.registerOutParameter(5, Types.BIGINT);
                return cs;
            },
            cs -> {
                cs.execute();
                return cs.getLong(5);
            }
        );

        Post post = new Post();
        post.setPostId(postId);
        post.setUserId(request.getUserId());
        post.setContent(request.getContent());
        post.setImage(request.getImage());
        post.setCreatedAt(createdAt);
        return new Response("Y", "新增發文成功", post);
    }

    public List<Post> getAllPosts() {
        String sql = "CALL get_all_posts()";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Post post = new Post();
            post.setPostId(rs.getLong("post_id"));
            post.setUserId(rs.getLong("user_id"));
            post.setUserName(rs.getString("user_name"));
            post.setContent(rs.getString("content"));
            post.setImage(rs.getString("image"));
            post.setCreatedAt(rs.getTimestamp("created_at"));
            return post;
        });
    }

    public Post getPostById(Long postId) {
        String sql = "CALL get_post_by_id(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{postId}, (rs, rowNum) -> {
            Post post = new Post();
            post.setPostId(rs.getLong("post_id"));
            post.setUserId(rs.getLong("user_id"));
            post.setContent(rs.getString("content"));
            post.setImage(rs.getString("image"));
            post.setCreatedAt(rs.getTimestamp("created_at"));
            return post;
        });
    }

    public void updatePost(Long postId, UpdatePostRequest request) {
        String sql = "CALL update_post(?, ?, ?)";
        jdbcTemplate.update(sql, postId, request.getContent(), request.getImage());
    }

    public void deletePost(Long postId) {
        String sql = "CALL delete_post(?)";
        jdbcTemplate.update(sql, postId);
    }
}
