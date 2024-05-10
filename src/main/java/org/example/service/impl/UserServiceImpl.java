package org.example.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.library.exeption.ResourceNotFoundException;
import org.example.library.models.entity.User;
import org.example.library.models.enums.Role;
import org.example.library.repasitory.UserRepository;
import org.example.library.service.UserService;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                new ResourceNotFoundException("User not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));
    }
    @Transactional
    public void update(User user) {
        Long userId = user.getId();
        if (userId != null) {
            Optional<User> optionalUser = userRepository.findById(userId);
            optionalUser.ifPresent(existingUser -> {
                existingUser.setName(user.getName());
                existingUser.setUsername(user.getUsername());
                existingUser.setPassword(user.getPassword());
                existingUser.setPasswordConfirmation(user.getPasswordConfirmation());
                existingUser.setEmail(user.getEmail());
                userRepository.save(existingUser);
            });
        } else {
            throw new InvalidDataAccessApiUsageException("User ID cannot be null");
        }
    }
    @Override
    public User create(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("User already exists.");
        }
        if (user.getPassword() == null || !user.getPassword().equals(user.getPasswordConfirmation())) {
            throw new IllegalStateException(
                    "Password and password confirmation do not match."
            );
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = Set.of(Role.ROLE_USER);
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}
