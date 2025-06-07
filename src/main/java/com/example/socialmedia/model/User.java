package com.example.socialmedia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String userName;
    private String email;
    private String account;
    
    @JsonIgnore
    private String password;
}
