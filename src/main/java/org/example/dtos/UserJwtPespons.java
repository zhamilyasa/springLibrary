package org.example.dtos;

import lombok.Data;

@Data
public class UserJwtPespons {
    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;
}
