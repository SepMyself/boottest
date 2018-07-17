package com.example.boottest.service;

import com.example.boottest.dao.entity.User;
import com.example.boottest.dao.entity.UserRole;
import com.example.boottest.dao.repository.UserRepository;
import com.example.boottest.util.JwtTokenUtil;
import com.example.boottest.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class AuthServiceImpl {//implements AuthService {
    private AuthenticationManager authenticationManager;
    private JwtUserDetailsServiceImpl jwtUserDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private DefaultUserService defaultUserService;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtUserDetailsServiceImpl jwtUserDetailsService,
                           JwtTokenUtil jwtTokenUtil,
                           DefaultUserService defaultUserService){
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.defaultUserService = defaultUserService;
    }

    //@Override
    public Integer register(User userToAdd){
        final String username = userToAdd.getUsername();
        if(defaultUserService.findByUsername(username) != null){
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setLastPasswordResetTime(TimeUtil.nowDate());
        Set<UserRole> userRoles = new HashSet<>();
        UserRole roles = new UserRole();
        roles.setUser(userToAdd);
        userRoles.add(roles);
        userToAdd.setUserRoleList(userRoles);
        Integer id = defaultUserService.addUser(userToAdd);
        return id;
    }

    //@Override
    public String login(String username, String password){
        return "";
    }
}
