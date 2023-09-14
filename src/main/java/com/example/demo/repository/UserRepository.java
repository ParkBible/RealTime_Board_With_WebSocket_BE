package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.OptionalLong;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNickname(String nickname);
}
