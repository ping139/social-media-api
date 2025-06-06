package com.example.socialmedia.vo;

import lombok.Data;

@Data
public class Response {
    private String result;
    private String message;
    private Object data;

    public Response(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public Response(String result, String message, Object data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }
}
