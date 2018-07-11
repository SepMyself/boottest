package com.example.boottest.controller;

import com.example.boottest.dao.entity.User;
import com.example.boottest.service.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private DefaultUserService service;

    @PostMapping("")
    public void insert(@RequestBody User user){
        service.addUser(user);
    }


    @GetMapping("/{id}")
    @ResponseBody
    public User findById(@PathVariable Integer id){
        return service.findById(id);
    }
}
