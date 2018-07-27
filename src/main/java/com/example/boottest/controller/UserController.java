package com.example.boottest.controller;

import com.example.boottest.dao.entity.User;
import com.example.boottest.dto.UserMapper;
import com.example.boottest.dto.UserRegisterDto;
import com.example.boottest.service.UserServiceImpl;
import com.example.boottest.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.internal.module.ModuleLoaderMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(tags = {"首页"})
public class UserController {
    private UserServiceImpl service;

    @Autowired
    public UserController(UserServiceImpl service){
        this.service = service;
    }

//    @PostMapping("")
//    @ApiOperation(value = "用户注册")
//    public void insert(@RequestBody User user){
//        service.addUser(user);
//    }


//    @GetMapping
//    @ResponseBody
//    @ApiOperation(value = "获取用户")
//    public List<User> getUsers(){
//        return service.findAll(0, 1);
//    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "根据用户Id获取用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    public UserRegisterDto findById(
            @PathVariable Integer id){
        // Test Area Start

        Integer userId = CommonUtil.getUserFromToken().getId();
        System.out.println(userId);
        // Test Area End

//        UserRegisterDto dto = new UserRegisterDto();
//        dto.setUsername("delores");
//        dto.setPassword("123456");
//        return dto;
        return service.findById(id);
    }
}
