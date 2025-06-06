package com.example.socialmedia.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = new BCryptPasswordEncoder(); // 用來加密密碼
    }

    public void registerUser(String userName, String email, String plainPassword) {
        // 1. 密碼加密
        String hashedPassword = passwordEncoder.encode(plainPassword);

        // 2. 呼叫 Stored Procedure 新增 user
        String sql = "CALL new_user(?, ?, ?)";
        jdbcTemplate.update(sql, userName, email, hashedPassword);
    }
}


