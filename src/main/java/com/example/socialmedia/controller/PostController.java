package com.example.socialmedia.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.socialmedia.model.Post;
import com.example.socialmedia.service.PostService;
import com.example.socialmedia.vo.CreatePostRequest;
import com.example.socialmedia.vo.Response;
import com.example.socialmedia.vo.UpdatePostRequest;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 取得全部文章
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // 新增文章
    @PostMapping
    public Response createPost(@RequestBody CreatePostRequest request) {
        return postService.createPost(request);
    }

    // 取得某篇文章內容
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    // 編輯文章
    @PutMapping("/{id}")
    public Response updatePost(@PathVariable Long id, @RequestBody UpdatePostRequest request) {
        try {
            postService.updatePost(id, request);
            Post post = postService.getPostById(id);
            return new Response("Y", "更新文章成功", post);
        } catch (Exception e) {
            return new Response("N", "更新文章失敗");
        }
    }

    // 刪除文章
    @DeleteMapping("/{id}")
    public Response deletePost(@PathVariable Long id) {
        try {
            postService.deletePost(id);
            return new Response("Y", "刪除文章成功");
        } catch (Exception e) {
            return new Response("N", "刪除文章失敗");
        }
    }
}
