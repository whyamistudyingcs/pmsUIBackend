package com.fdm.pmsuibackend.controller;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.pmsuibackend.dto.UserCreationRequestDto;
import com.fdm.pmsuibackend.dto.UserRegistrationResponse;
import com.fdm.pmsuibackend.dto.UserLoginDto;
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
    public ResponseEntity<UserRegistrationResponse> register(@RequestBody UserCreationRequestDto userCreationRequestDto) {

        UserRegistrationResponse response;
        try {
            response = authService.register(userCreationRequestDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new UserRegistrationResponse();
            response.setUsername(userCreationRequestDto.getUsername());
            response.setStatus("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(214)).body(response);
        }

    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDto userLoginDto) {

        try {
            String response = authService.login(userLoginDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(e.getMessage());
        }
    }
    
}
