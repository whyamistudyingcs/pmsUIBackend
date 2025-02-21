package com.fdm.pmsuibackend.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author April Chou
 * @Classname User
 * @Description TODO
 * @Version 1.0
 * @Date 2025/1/23 22:16
 */
//1. User Login, register:
//
//Type: API
//
//Fields: Username, password
//
//
//
//2. Portfolio Data of a particular user
//
//Type: Websocket
//
//Fields: Ticker, Quantity, average_price, current_price (from this WebSocket or a different one?)
//
@Data
@Component
public class UserDto {
    private String username;
    private String password;
}
