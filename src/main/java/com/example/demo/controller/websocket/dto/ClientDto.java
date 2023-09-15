package com.example.demo.controller.websocket.dto;

import lombok.Builder;
import lombok.Data;
import jakarta.websocket.Session;

@Data
@Builder
public class ClientDto {
    public Session session;
    public String nickname;
    public Integer postSeq;
}
