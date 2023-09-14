package com.example.demo.controller.post;

import com.example.demo.controller.post.dto.DetailResponse;
import com.example.demo.controller.post.dto.PostsResponse;
import com.example.demo.controller.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    public void post() {

    }

    @GetMapping("/{seq}")
    public DetailResponse getPost(@PathVariable int seq) {
        return postService.getPost(seq);
    }

    @GetMapping
    public PostsResponse getPosts() {
        return postService.getPosts();
    }
}
