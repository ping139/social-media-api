package com.example.socialmedia.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.socialmedia.model.User;
import com.example.socialmedia.vo.RegisterRequest;
import com.example.socialmedia.vo.Response;

@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = new BCryptPasswordEncoder(); // 用來加密密碼
    }

    // 註冊功能
    public void registerUser(RegisterRequest request) {
        // 1. 密碼加密
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // 2. 呼叫 Stored Procedure 新增 user
        String sql = "CALL new_user(?, ?, ?, ?)";
        jdbcTemplate.update(sql, request.getAccount(), request.getUserName(), request.getEmail(), hashedPassword);
    }

    // 登入功能
    public Response login(String account, String rawPassword) {
        String sql = "CALL login_user(?)";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{account}, (rs, rowNum) -> {
            User u = new User();
            u.setUserId(rs.getLong("user_id"));
            u.setUserName(rs.getString("user_name"));
            u.setEmail(rs.getString("email"));
            u.setAccount(rs.getString("account"));
            u.setPassword(rs.getString("password"));
            return u;
        });

        // 比對密碼
        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            return new Response("Y", "登入成功", user);
        } else {
            return new Response("N", "密碼錯誤");
        }
    }
}


