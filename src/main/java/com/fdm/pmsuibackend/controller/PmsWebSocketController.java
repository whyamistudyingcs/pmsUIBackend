package com.fdm.pmsuibackend.controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author April Chou
 * @Classname PmsWebSocketController
 * @Description TODO
 * @Version 1.0
 * @Date 2025/2/5 21:50
 */
@Controller
public class PmsWebSocketController {
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public String handleMessage(String message) {
        // Process the incoming message and return a response
        return "Received: " + message;
    }
}



