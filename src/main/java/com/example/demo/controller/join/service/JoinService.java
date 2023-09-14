package com.example.demo.controller.join.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;

    public void join(String nickname) {
        userRepository.save(
                User.builder()
                .nickname(nickname)
                .build()
        );
    }
}
