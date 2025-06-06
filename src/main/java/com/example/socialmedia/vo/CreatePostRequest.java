package com.example.socialmedia.vo;

import lombok.Data;

@Data
public class CreatePostRequest {
    private Long userId;
    private String content;
    private String image;
}
