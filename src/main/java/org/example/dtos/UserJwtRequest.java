package org.example.dtos;

import lombok.Data;

@Data
public class UserJwtRequest {
    private String username;
    private String password;
}
