package com.example.boottest.service;

import com.example.boottest.dao.entity.User;

import java.util.List;

public interface UserService {
    Integer addUser(User user);
    User findById(Integer id);
    List<User> findAll(int page, int size);
    User findByUsername(String username);
}
