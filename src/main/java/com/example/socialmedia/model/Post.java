package com.example.socialmedia.model;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class Post {
    private Long postId;
    private String userName;
    private Long userId;
    private String content;
    private String image;
    private Timestamp createdAt;
}
