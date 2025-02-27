package com.fdm.pmsuibackend.service;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fdm.pmsuibackend.dto.UserCreationRequestDto;
import com.fdm.pmsuibackend.dto.UserRegistrationResponse;
import com.fdm.pmsuibackend.dto.mapper.UserMapper;
import com.fdm.pmsuibackend.model.User;
import com.fdm.pmsuibackend.repositories.UserRepository;
import com.fdm.pmsuibackend.service.security.JWTService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;
    private JWTService jwtService;

    @Transactional
    public UserRegistrationResponse createUser(UserCreationRequestDto userCreationRequestDto) {
        if (userRepository.existsByUsername(userCreationRequestDto.getUsername())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Username already exists"
            );
        }
        // encode password in encoder
        User user = userMapper.creationRequestToUser(userCreationRequestDto);
        return userMapper.toUserDto(userRepository.save(user));
    }
}
