package com.example.socialmedia.model;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String userName;
    private String email;
    private String password;
}
