package com.example.socialmedia.vo;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String userName;
    private String password;
}
