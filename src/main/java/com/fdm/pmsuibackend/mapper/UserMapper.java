package com.fdm.pmsuibackend.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fdm.pmscommon.dto.requests.UserCreationRequestDto;
import com.fdm.pmscommon.dto.responses.UserDto;
import com.fdm.pmscommon.entities.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder encoder;

    public User creationRequestToUser(UserCreationRequestDto userCreationRequestDto) {
        User user = new User();
        user.setUsername(userCreationRequestDto.getUsername());
        user.setPassword(encoder.encode(userCreationRequestDto.getPassword()));
        return user;
    }

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}
