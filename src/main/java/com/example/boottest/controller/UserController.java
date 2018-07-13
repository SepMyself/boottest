package com.example.boottest.controller;

import com.example.boottest.config.CustomConfig;
import com.example.boottest.dao.entity.User;
import com.example.boottest.service.DefaultUserService;
import com.example.boottest.util.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private DefaultUserService service;
    @Autowired
    private CustomConfig customConfig;

    @PostMapping("")
    public void insert(@RequestBody User user){
        service.addUser(user);
    }


    @GetMapping("/{id}")
    @ResponseBody
    //@Authorize(role = "admin")
    public User findById(@PathVariable Integer id){
        //something wrong
        //String token = TokenHelper.toToken(null, customConfig.getApiKey());
        //System.out.println(token);

        // Test Area Start
        //System.out.println(5 / 0.0);
        //System.out.println(-5 / 0.0);
        //System.out.println(-5 / 0);
        System.out.println(-5.1 / 0);


        // Test Area End

        return service.findById(id);
    }
}
