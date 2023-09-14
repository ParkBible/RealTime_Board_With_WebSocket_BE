package com.example.demo.controller.post.dto;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class RepleResponse {
    public int seq;
    public String nickname;
    public String content;
}
