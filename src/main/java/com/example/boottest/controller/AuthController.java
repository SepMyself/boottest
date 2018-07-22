package com.example.boottest.controller;

import com.example.boottest.dao.entity.User;
import com.example.boottest.dto.UserRegisterDto;
import com.example.boottest.service.AuthService;
import com.example.boottest.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody UserRegisterDto dto)
            throws AuthenticationException {
        return authService.login(dto.getUsername(), dto.getPassword());
    }

    @GetMapping("/refresh")
    @ResponseBody
    public String refreshToken(HttpServletRequest request)
            throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        return authService.refresh(token);
    }

    @PostMapping("/register")
    @ResponseBody
    public Integer register(@RequestBody UserRegisterDto user)
            throws AuthenticationException{
        return authService.register(user);
    }
}
