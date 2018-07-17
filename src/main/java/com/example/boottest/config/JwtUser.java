package com.example.boottest.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {
    private static final long serialVersionUID = 5L;

    private final String id;
    private final String username;
    private final String password;
    private final String email;
    private final String salt;
    private final byte status;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Date lastPasswordResetDate;

    public JwtUser(
            String id,
            String username,
            String password,
            String email,
            String salt,
            byte status,
            Collection<? extends GrantedAuthority> authorities,
            Date lastPasswordResetDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.salt = salt;
        this.status = status;
        this.authorities = authorities;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked(){
        return isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired(){
        // token生成时间早于密码修改时间, 则为false
        // 否则为true
        return true;
    }

    // 账户是否未过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    // 账户是否激活
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return status == 0;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public String getSalt() {
        return salt;
    }

    public String getEmail() {
        return email;
    }
}
