package com.example.boottest.config;

import com.example.boottest.dao.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    private JwtUserFactory(){}

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId().toString(),
                user.getUsername(),
                user.getPassword(),
                "email",
                user.getSalt(),
                user.getStatus(),
                mapToGrantedAuthorities(new ArrayList<String>()),
                user.getLastPasswordResetTime()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities){
        authorities = new ArrayList<String>();
        authorities.add("admin");
        authorities.add("superadmin");
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
