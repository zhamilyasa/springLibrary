package org.example.library.service;


import org.example.library.dtos.UserJwtPespons;
import org.example.library.dtos.UserJwtRequest;

public interface AuthService {
    UserJwtPespons login(final UserJwtRequest loginRequest);
}
