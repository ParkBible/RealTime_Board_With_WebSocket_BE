package com.example.demo.controller.websocket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {
    public Integer type;
}
