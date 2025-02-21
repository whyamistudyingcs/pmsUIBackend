package com.fdm.pmsuibackend.service.security;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fdm.pmsuibackend.model.User;
import com.fdm.pmsuibackend.repositories.UserRepository;
import com.fdm.pmsuibackend.details.UserPrincipal;


@Service
public class CustomizedUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomizedUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "User not found with username: " + username
            );
        }
        return new UserPrincipal(userOptional.get());
    }

}
