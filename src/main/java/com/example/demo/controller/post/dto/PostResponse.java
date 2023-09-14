package com.example.demo.controller.post.dto;

import lombok.Builder;

@Builder
public class PostResponse {
    public int seq;
    public String nickname;
    public String title;
}