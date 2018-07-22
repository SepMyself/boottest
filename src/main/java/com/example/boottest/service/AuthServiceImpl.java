package com.example.boottest.service;

import com.example.boottest.dao.entity.Role;
import com.example.boottest.dao.entity.User;
import com.example.boottest.dao.repository.RoleRepository;
import com.example.boottest.dto.UserMapper;
import com.example.boottest.dto.UserRegisterDto;
import com.example.boottest.util.CommonUtil;
import com.example.boottest.util.JwtTokenUtil;
import com.example.boottest.util.Md5Util;
import com.example.boottest.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;
    private RoleRepository roleRepository;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService,
                           JwtTokenUtil jwtTokenUtil,
                           UserService userService,
                           RoleRepository roleRepository){
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Integer register(UserRegisterDto dto){
        User userToAdd = UserMapper.INSTANCE.dtoToEntity(dto);
        final String username = userToAdd.getUsername();
        if(userService.findByUsername(username) != null){
            return null;
        }
        String rawPassword = userToAdd.getPassword();

        String salt = CommonUtil.getSalt(6);
        rawPassword = Md5Util.MD5(Md5Util.MD5(rawPassword) + salt);

        Date now = TimeUtil.nowDate();
        userToAdd.setPassword(rawPassword);
        userToAdd.setSalt(salt);
        userToAdd.setStatus((byte)0);
        userToAdd.setLastPasswordResetDate(TimeUtil.nowDate());
        userToAdd.setLastLoginTime(now);
        Role role = roleRepository.getOne(1);
        userToAdd.setRole(role);
        userToAdd.setCreateTime(now);
        return userService.addUser(userToAdd);
    }

    @Override
    public String login(String username, String password){
        User user = userService.findByUsername(username);
        if(Md5Util.MD5(Md5Util.MD5(password) + user.getSalt()).equals(user.getPassword())){
            final String token = jwtTokenUtil.generateToken(user);
            return token;
        }
        return null;
    }

    @Override
    public String refresh(String oldToken){
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.findByUsername(username);
        if(jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
