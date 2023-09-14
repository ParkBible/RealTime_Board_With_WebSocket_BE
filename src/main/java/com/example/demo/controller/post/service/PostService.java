package com.example.demo.controller.post.service;

import com.example.demo.controller.post.dto.DetailResponse;
import com.example.demo.controller.post.dto.PostResponse;
import com.example.demo.controller.post.dto.PostsResponse;
import com.example.demo.controller.post.dto.RepleResponse;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public DetailResponse getPost(int seq) {
        Optional<Post> foundPost = postRepository.findById(seq);

        if (foundPost.isPresent()) {
            Post post = foundPost.get();
            return DetailResponse.builder()
                    .title(post.getTitle())
                    .content(post.getContent())
                    .reples(post.getReples().stream().map(reple ->
                            RepleResponse.builder()
                            .seq(reple.getSeq())
                            .nickname(reple.getUser().getNickname())
                            .content(reple.getContent())
                            .build()).toList())
                    .build();
        }
        return null;
    }

    public PostsResponse getPosts() {
        List<Post> posts = postRepository.findAll();

        return PostsResponse.builder()
                .posts(posts.stream().map(post ->
                        PostResponse.builder()
                        .seq(post.getSeq())
                        .nickname(post.getUser().getNickname())
                        .title(post.getTitle())
                        .build()).toList())
                .build();
    }
}
