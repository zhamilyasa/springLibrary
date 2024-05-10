package org.example.library.security;

import lombok.RequiredArgsConstructor;
import org.example.library.models.entity.User;
import org.example.library.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        User user = userService.getByUsername(username);
        return JwtEntityFactory.create(user);
    }

}
