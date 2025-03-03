package com.fdm.pmsuibackend.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fdm.pmscommon.dto.incoming.UserCreationRequestDto;
import com.fdm.pmscommon.dto.outgoing.UserDto;
import com.fdm.pmscommon.entities.User;
import com.fdm.pmscommon.repositories.UserRepository;
import com.fdm.pmsuibackend.mapper.UserMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserDto createUser(UserCreationRequestDto userCreationRequestDto) {
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
