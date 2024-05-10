package org.example.library.service;


import org.example.library.models.entity.User;

public interface UserService {
    User getById(Long id);

    User getByUsername(String username);

    void update(User user);

    User create(User user);

    void delete(Long id);
}
