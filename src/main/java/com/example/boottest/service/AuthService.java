package com.example.boottest.service;

import com.example.boottest.dao.entity.User;

public interface AuthService {
    Integer register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
