package com.example.demo.controller.websocket.service;

import com.example.demo.controller.websocket.constants.MessageType;
import com.example.demo.controller.websocket.dto.MessageRequest;
import com.example.demo.controller.websocket.dto.MessageResponse;
import com.example.demo.model.Post;
import com.example.demo.model.Reple;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.RepleRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.example.demo.controller.websocket.service.WebSocketService.CLIENTS;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final RepleRepository repleRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void sendMessage(Session session, MessageRequest messageRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if (messageRequest.type == MessageType.NICKNAME.ordinal()) {
            CLIENTS.forEach(client -> {
                if (client.session == session) {
                    client.nickname = messageRequest.getNickname();
                }
            });
        } else if (messageRequest.type == MessageType.ENTER.ordinal()) {
            CLIENTS.forEach(client -> {
                if (client.session == session) {
                    client.postSeq = messageRequest.getPostSeq();
                }
            });
        } else if (messageRequest.type == MessageType.WRITE_REPLE.ordinal()) {
            CLIENTS.forEach(client -> {
                // 글을 보고 있는 사람들에게 알림
                if (Objects.equals(client.postSeq, messageRequest.getPostSeq()) &&
                        !Objects.equals(client.nickname, messageRequest.getNickname())) {
                    try {
                        MessageResponse messageResponse = MessageResponse.builder()
                                .type(MessageType.NEW_REPLE.ordinal())
                                .build();

                        client.session.getBasicRemote().sendText(objectMapper.writeValueAsString(messageResponse));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                // 글을 쓴 사람에게 알림
                Optional<Post> foundPost = postRepository.findById(messageRequest.postSeq);

                foundPost.ifPresent(post -> {
                    if (Objects.equals(client.nickname, post.getUser().getNickname())) {
                        try {
                            MessageResponse messageResponse = MessageResponse.builder()
                                    .type(MessageType.ALERT_REPLE.ordinal())
                                    .build();

                            client.session.getBasicRemote().sendText(objectMapper.writeValueAsString(messageResponse));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            });

            repleRepository.save(Reple.builder()
                    .content(messageRequest.getContent())
                    .postSeq(messageRequest.postSeq)
                    .user(userRepository.findByNickname(messageRequest.getNickname()).orElse(null))
                    .build()
            );
        }
    }
}
