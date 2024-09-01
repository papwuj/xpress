package com.ruoyaya.xpress.commons.utils;

import cn.hutool.jwt.JWT;

import java.util.Date;

public class JwtUtil {

    private final static String secretKey = "com.ruoyaya.xpress";
    private final static byte[] SECRET_KEY_BYTES = secretKey.getBytes();
    private final static long EXPIRES_TIME = 3600000;
    private final static long EXPIRES_TIME_REMEMBER_ME = EXPIRES_TIME * 24 * 30;

    public static String generateToken(String username, boolean isRememberMe) {
        return JWT.create()
                .setPayload("username", username) // 设置payload中的数据
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 设置发布时间为当前时间
                .setExpiresAt(new Date(System.currentTimeMillis() + (isRememberMe? EXPIRES_TIME_REMEMBER_ME: EXPIRES_TIME) )) // 设置过期时间为1小时后
                .setKey(SECRET_KEY_BYTES) // 设置密钥
                .sign(); // 生成JWT
    }

    public static boolean checkToken(String token) {
        return JWT.of(token).setKey(SECRET_KEY_BYTES).verify();
    }


    public static void main(String[] args) {
        String username = generateToken("username", true);
        System.out.println(username);
    }
}
