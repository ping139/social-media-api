package com.example.socialmedia.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Comment {
    private Long commentId;
    private Long userId;
    private Long postId;
    private String userName;
    private String content;
    private Timestamp createdAt;
}
