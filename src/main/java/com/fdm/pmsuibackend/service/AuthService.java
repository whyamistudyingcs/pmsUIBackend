package com.fdm.pmsuibackend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.fdm.pmsuibackend.dto.UserCreationRequestDto;
import com.fdm.pmsuibackend.dto.UserDto;
import com.fdm.pmsuibackend.dto.UserLoginDto;
import com.fdm.pmsuibackend.service.security.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;

    public UserDto register(UserCreationRequestDto userCreationRequestDto) {
        return userService.createUser(userCreationRequestDto);
    }

    public String login(UserLoginDto userLoginDto) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userLoginDto.getUsername(),
                userLoginDto.getPassword()
            )
        );
        if (auth.isAuthenticated()) {
            return jwtService.generateToken(userLoginDto.getUsername());
        } else {
            return "Authentication failed";
        }
    }
}
