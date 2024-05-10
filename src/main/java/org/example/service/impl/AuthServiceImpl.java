package org.example.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.library.dtos.UserJwtPespons;
import org.example.library.dtos.UserJwtRequest;
import org.example.library.models.entity.User;
import org.example.library.security.JwtTokenProvider;
import org.example.library.service.AuthService;
import org.example.library.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserJwtPespons login(final UserJwtRequest loginRequest) {
        UserJwtPespons jwtResponse = new UserJwtPespons();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword())
        );
        User user = userService.getByUsername(loginRequest.getUsername());
        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken("Breare " + jwtTokenProvider.createAccessToken(
                user.getId(), user.getUsername(), user.getRoles())
        );
        jwtResponse.setRefreshToken(jwtTokenProvider.createAccessToken(
                user.getId(), user.getUsername(), user.getRoles())
        );
        return jwtResponse;
    }


}
