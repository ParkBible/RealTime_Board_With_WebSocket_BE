package com.example.demo.controller.websocket.service;

import com.example.demo.controller.websocket.dto.ClientDto;
import com.example.demo.controller.websocket.dto.MessageRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/test")
@Service
@RequiredArgsConstructor
public class WebSocketService {
    private final MessageService messageService;

    public static final Set<ClientDto> CLIENTS = Collections.synchronizedSet(new HashSet<>());
//    private static final Set<String> NICKNAMES = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.toString());

        if (CLIENTS.stream().anyMatch(client -> client.session == session)) {
            System.out.println("이미 연결된 세션입니다. > " + session);
        } else {
            CLIENTS.add(ClientDto.builder().session(session).build());
            System.out.println("새로운 세션입니다. > " + session);
        }
    }

    @OnClose
    public void onClose(Session session) throws Exception {
        CLIENTS.removeIf(client -> client.session == session);
        System.out.println("세션을 닫습니다. : " + session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        System.out.println(session + "에 의해 입력된 메세지입니다. > " + message);
        parseMessage(session, message);

        for (Session client : CLIENTS.stream().map(clientDto -> clientDto.session).toArray(Session[]::new)) {
            System.out.println("메세지를 전달합니다. > " + message);
            client.getBasicRemote().sendText(message);
        }
    }

    private void parseMessage(Session session, String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            MessageRequest messageRequest = objectMapper.readValue(message, MessageRequest.class);
            messageService.sendMessage(session, messageRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
