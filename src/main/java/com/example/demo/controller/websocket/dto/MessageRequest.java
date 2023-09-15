package com.example.demo.controller.websocket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageRequest {
    public Integer type;
    public Integer postSeq;
    public String nickname;
    public String content;
}
