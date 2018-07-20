package com.example.boottest.util;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Md5Encoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword){
        return Md5Util.MD5(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword){
        return encodedPassword.equals(encode(rawPassword));
    }
}
