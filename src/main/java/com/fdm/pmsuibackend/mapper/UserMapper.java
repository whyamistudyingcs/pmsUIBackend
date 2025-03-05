package com.fdm.pmsuibackend.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fdm.pmscommon.dto.incoming.UserCreationRequestDto;
import com.fdm.pmscommon.dto.outgoing.UserRegistrationResponse;
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

    public UserRegistrationResponse toUserDto(User user) {
        UserRegistrationResponse userRegistrationResponse = new UserRegistrationResponse();
        userRegistrationResponse.setUserId(user.getId());
        userRegistrationResponse.setUsername(user.getUsername());
        userRegistrationResponse.setStatus("Success");
        return userRegistrationResponse;
    }
}
