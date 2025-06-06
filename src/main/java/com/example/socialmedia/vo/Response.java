package com.example.socialmedia.vo;

import lombok.Data;

@Data
public class Response {
    private String result;
    private String message;

    public Response(String result, String message) {
        this.result = result;
        this.message = message;
    }
}
