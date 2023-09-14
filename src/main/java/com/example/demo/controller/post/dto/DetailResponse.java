package com.example.demo.controller.post.dto;

import lombok.Builder;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
public class DetailResponse {
    public String title;
    public String content;
    public List<RepleResponse> reples;
}
