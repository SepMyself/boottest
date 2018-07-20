package com.example.boottest.controller;

import com.example.boottest.dao.entity.User;
import com.example.boottest.dto.UserRegisterDto;
import com.example.boottest.service.AuthService;
import com.example.boottest.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private String tokenHeader = "Authorization";
    private String tokenHead = "Bearer ";
    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody UserRegisterDto dto) throws AuthenticationException {
        final String token = authService.login(dto.getUsername(), dto.getPassword());
        return token;
    }

    @GetMapping()
    @ResponseBody
    public String refreshToken(HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshToken = authService.refresh(token);
        return refreshToken;
    }

    @PostMapping()
    @ResponseBody
    public Integer register(@RequestBody UserRegisterDto user) throws AuthenticationException{
        return authService.register(user);
    }
}
