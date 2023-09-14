package com.example.demo.controller.login;

import com.example.demo.controller.login.dto.LoginRequest;
import com.example.demo.controller.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<Integer> login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest.getNickname());
    }
}
