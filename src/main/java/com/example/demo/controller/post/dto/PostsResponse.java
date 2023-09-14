package com.example.demo.controller.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostsResponse {
    public List<PostResponse> posts = new ArrayList<>();
}