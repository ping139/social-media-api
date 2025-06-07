package com.example.socialmedia.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.socialmedia.service.UserService;
import com.example.socialmedia.vo.LoginRequest;
import com.example.socialmedia.vo.RegisterRequest;
import com.example.socialmedia.vo.Response;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public Response register(@RequestBody RegisterRequest request) {
        try {
            userService.registerUser(request);
            return new Response("Y", "註冊成功");
        } catch (Exception e) {
            return new Response("N", "註冊失敗：" + e.getMessage());
        }
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginRequest request) {
        return userService.login(request.getAccount(), request.getPassword());
    }
}

