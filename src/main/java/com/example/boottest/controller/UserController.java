package com.example.boottest.controller;

import com.example.boottest.config.CustomConfig;
import com.example.boottest.dao.entity.User;
import com.example.boottest.service.DefaultUserService;
import com.example.boottest.util.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('admin')")
public class UserController {
    private DefaultUserService service;
    //private CustomConfig customConfig;

    @Autowired
    public UserController(DefaultUserService service){//, CustomConfig customConfig){
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
        //System.out.println(-5.1 / 0);


        // Test Area End

        return service.findById(id);
    }
}
