package com.fdm.pmsuibackend.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.fdm.pmscommon.dto.incoming.UserCreationRequestDto;
import com.fdm.pmscommon.dto.incoming.UserLoginDto;
import com.fdm.pmscommon.dto.outgoing.UserRegistrationResponse;
import com.fdm.pmsuibackend.service.security.JWTService;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;


    private static final Validator validator;

    static{
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    public UserRegistrationResponse register(UserCreationRequestDto userCreationRequestDto) {

        Set<ConstraintViolation<UserCreationRequestDto>> violationSet = validator.validate(userCreationRequestDto);
        if(violationSet.size() > 0) {
            throw new IllegalArgumentException(violationSet.iterator().next().getMessage());
        }
        return userService.createUser(userCreationRequestDto);
    }

    public String login(UserLoginDto userLoginDto) {

        Set<ConstraintViolation<UserLoginDto>> violationSet = validator.validate(userLoginDto);
        if(violationSet.size() > 0) {
            throw new IllegalArgumentException(violationSet.iterator().next().getMessage());
        }

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
