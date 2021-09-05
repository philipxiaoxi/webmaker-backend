package com.lazyfish.codeshare.utils;

import java.util.Random;

public class StringUtils {
    private final static Random random = new Random();
    private final static String numberTemplate = "0123456789";
    public static String getRandomString(int len) {
        int tl = numberTemplate.length();
        StringBuilder code = new StringBuilder();
        for(int i = 0;i < len; i++) {
            char c = numberTemplate.charAt(Math.abs(random.nextInt() % tl));
            code.append(c);
        }
        return code.toString();
    }
}
