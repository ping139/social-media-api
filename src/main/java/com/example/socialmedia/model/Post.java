package com.example.socialmedia.model;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class Post {
    private Long postId;
    private Long userId;
    private String content;
    private String image;
    private Timestamp createdAt;
}
