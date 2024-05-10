package org.example.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.library.dtos.UserDto;
import org.example.library.mapper.UserMapper;
import org.example.library.models.entity.User;
import org.example.library.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "User Controller", description = "User API")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PutMapping(name = "/update")
    @Operation(summary = "Update user")
    public void update(@RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        userService.update(user);
    }

    @GetMapping("/get/{id}")
    @CrossOrigin
    @Operation(summary = "Get UserDto by id")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

}
