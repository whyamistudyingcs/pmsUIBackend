package com.fdm.pmsuibackend.dto.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fdm.pmsuibackend.dto.UserCreationRequestDto;
import com.fdm.pmsuibackend.dto.UserRegistrationResponse;
import com.fdm.pmsuibackend.model.User;

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

    public UserRegistrationResponse toUserDto(User user) {
        UserRegistrationResponse userRegistrationResponse = new UserRegistrationResponse();
        userRegistrationResponse.setUserId(user.getId());
        userRegistrationResponse.setUsername(user.getUsername());
        userRegistrationResponse.setStatus("Success");
        return userRegistrationResponse;
    }
}
