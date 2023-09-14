package com.example.demo.controller.websocket.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/test")
@Service
public class TestService {
    private static final Set<Session> CLIENTS = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.toString());

        if (CLIENTS.contains(session)) {
            System.out.println("이미 연결된 세션입니다. > " + session);
        } else {
            CLIENTS.add(session);
            System.out.println("새로운 세션입니다. > " + session);
        }
    }

    @OnClose
    public void onClose(Session session) throws Exception {
        CLIENTS.remove(session);
        System.out.println("세션을 닫습니다. : " + session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        System.out.println(session + "에 의해 입력된 메세지입니다. > " + message);

        for (Session client : CLIENTS) {
            System.out.println("메세지를 전달합니다. > " + message);
            client.getBasicRemote().sendText(message);
        }
    }
}
