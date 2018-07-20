package com.example.boottest.service;

import com.example.boottest.config.JwtUser;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashSet;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private JwtUserDetailsServiceImpl jwtUserDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private UserService defaultUserService;
    private String tokenHead = "Bearer ";
    private RoleRepository roleRepository;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtUserDetailsServiceImpl jwtUserDetailsService,
                           JwtTokenUtil jwtTokenUtil,
                           UserService defaultUserService,
                           RoleRepository roleRepository){
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.defaultUserService = defaultUserService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Integer register(UserRegisterDto dto){
        User userToAdd = UserMapper.INSTANCE.dtoToEntity(dto);
        final String username = userToAdd.getUsername();
        if(defaultUserService.findByUsername(username) != null){
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = userToAdd.getPassword();

        String salt = CommonUtil.getSalt(6);
        rawPassword = Md5Util.MD5(Md5Util.MD5(rawPassword) + salt);

        Date now = TimeUtil.nowDate();
        userToAdd.setPassword(rawPassword);
        userToAdd.setSalt(salt);
        userToAdd.setStatus((byte)0);
        userToAdd.setLastPasswordResetTime(TimeUtil.nowDate());
        userToAdd.setLastLoginTime(now);
        Role role = roleRepository.getOne(1);
        userToAdd.setRole(role);
        userToAdd.setCreateTime(now);
        return defaultUserService.addUser(userToAdd);
    }

    @Override
    public String login(String username, String password){
        User user = defaultUserService.findByUsername(username);
        if(Md5Util.MD5(Md5Util.MD5(password) + user.getSalt()).equals(user.getPassword())){
//            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
//            final Authentication authentication = authenticationManager.authenticate(upToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);

            final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
            final String token = jwtTokenUtil.generateToken(userDetails);
            return token;
        }
        return null;
    }

    @Override
    public String refresh(String oldToken){
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser)jwtUserDetailsService.loadUserByUsername(username);
        if(jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
