package com.fdm.pmsuibackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.pmscommon.dto.incoming.UserCreationRequestDto;
import com.fdm.pmscommon.dto.incoming.UserLoginDto;
import com.fdm.pmscommon.dto.outgoing.UserDto;
import com.fdm.pmsuibackend.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserCreationRequestDto userCreationRequestDto) {
        return ResponseEntity.ok(authService.register(userCreationRequestDto));
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(authService.login(userLoginDto));
    }
    
}
