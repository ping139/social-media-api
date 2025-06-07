package com.example.socialmedia.vo;

import lombok.Data;

@Data
public class RegisterRequest {
    private String account;
    private String email;
    private String userName;
    private String password;
}
