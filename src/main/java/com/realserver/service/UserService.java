package com.realserver.service;

import com.realserver.model.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByUserName();

    User findById(Long id);

    void delete(Long id);
}
