package com.example.boottest.util;

import java.sql.Timestamp;
import java.util.Date;

public class TimeUtil {
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }
    public static Date nowDate() {
        return new Date(System.currentTimeMillis());
    }
}
