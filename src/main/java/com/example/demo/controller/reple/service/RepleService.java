package com.example.demo.controller.reple.service;

import com.example.demo.controller.post.dto.RepleResponse;
import com.example.demo.model.Reple;
import com.example.demo.repository.RepleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepleService {
    private final RepleRepository repleRepository;

    public void getReple(int seq) {
        Optional<Reple> foundReple = repleRepository.findById(seq);

    }
}
