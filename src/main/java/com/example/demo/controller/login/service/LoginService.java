package com.example.demo.controller.login.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final UserRepository userRepository;
    public ResponseEntity<Integer> login(String nickname) {
        Optional<User> foundUser = userRepository.findByNickname(nickname);

        if (foundUser.isPresent()) {
            User user = foundUser.get();
            return new ResponseEntity<>(user.getSeq(), HttpStatus.OK);
        }

        return new ResponseEntity<>(0, HttpStatus.UNAUTHORIZED);
    }
}
