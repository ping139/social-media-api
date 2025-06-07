package com.example.socialmedia.controller;

import com.example.socialmedia.model.Comment;
import com.example.socialmedia.service.CommentService;
import com.example.socialmedia.vo.CreateCommentRequest;
import com.example.socialmedia.vo.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 新增留言
    @PostMapping
    public Response createComment(@RequestBody CreateCommentRequest request) {
        try {
            commentService.createComment(request);
            return new Response("Y", "新增留言成功");
        } catch (Exception e) {
            return new Response("N", e.getMessage());
        }
    }

    // 查詢某篇文章的所有留言
    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }
}
