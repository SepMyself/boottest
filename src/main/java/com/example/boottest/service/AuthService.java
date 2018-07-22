package com.example.boottest.service;

import com.example.boottest.dao.entity.User;
import com.example.boottest.dto.UserRegisterDto;

public interface AuthService {
    Integer register(UserRegisterDto userDto);
    String login(String username, String password);
    String refresh(String oldToken);
}
