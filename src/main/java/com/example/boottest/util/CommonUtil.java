package com.example.boottest.util;

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
}
