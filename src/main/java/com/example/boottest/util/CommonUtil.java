package com.example.boottest.util;

import com.example.boottest.dao.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Random;

public class CommonUtil {
    private static Random random = new Random();

    private static String getRandomString(int length){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < length; i++){
            stringBuilder.append(random.nextInt(9));
        }
        return stringBuilder.toString();
    }

    public static String getSalt(int length) {
        return getRandomString(length);
    }

    public static User getUserFromToken(){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
