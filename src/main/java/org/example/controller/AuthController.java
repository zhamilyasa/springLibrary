package org.example.library.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.library.dtos.UserDto;
import org.example.library.dtos.UserJwtPespons;
import org.example.library.dtos.UserJwtRequest;
import org.example.library.mapper.UserMapper;
import org.example.library.models.entity.User;
import org.example.library.service.AuthService;
import org.example.library.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    @Operation(summary = "Login In")
    public UserJwtPespons login(@Validated
                             @RequestBody final UserJwtRequest  loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    @Operation(summary = "Register In")
    public UserDto register(@RequestBody final UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }
}