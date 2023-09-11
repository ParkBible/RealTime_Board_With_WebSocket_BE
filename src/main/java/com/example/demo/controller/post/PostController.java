package com.example.demo.controller.post;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/post")
public class PostController {

    @PostMapping
    public void post() {

    }

    @GetMapping("/{seq}")
    public void getPost() {

    }
}
