package com.example.boottest.controller;

import com.example.boottest.dao.entity.User;
import com.example.boottest.dto.UserRegisterDto;
import com.example.boottest.service.UserServiceImpl;
import com.example.boottest.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {
    private UserServiceImpl service;
    //private CustomConfig customConfig;

    @Autowired
    public UserController(UserServiceImpl service){//, CustomConfig customConfig){
        this.service = service;
        //this.customConfig = customConfig;
    }

    @PostMapping("")
    public void insert(@RequestBody User user){
        service.addUser(user);
    }


    @GetMapping
    @ResponseBody
    public List<User> getUsers(){
        return service.findAll(0, 1);
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/{id}")
    @ResponseBody
    public UserRegisterDto findById(@PathVariable Integer id){
        // Test Area Start

        CommonUtil.getUserFromToken().getId();
        // Test Area End

        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername("delores");
        dto.setPassword("123456");
        return dto;
        //return service.findById(id);
    }
}
