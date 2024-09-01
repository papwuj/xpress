package com.ruoyaya.xpress.commons.utils;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;

public class PasswordEncoding {
    public static void main(String[] args) {
        String originalPassword = "mySecretPassword!123";
        String salt = generateSalt();
        System.out.println("Salt: " + salt);

        // 加密密码，自动生成盐值
        String generatedSecuredPasswordHash = encodePassword(originalPassword, salt);
        System.out.println("Hashed password: " + generatedSecuredPasswordHash);

        // 检查输入的密码是否与存储的密码哈希匹配
        boolean matched = checkPassword(originalPassword, generatedSecuredPasswordHash, salt);
        System.out.println("Password matched: " + matched);

    }

    public static String generateSalt() {
        byte[] key = SecureUtil.generateKey(HmacAlgorithm.HmacSHA256.getValue()).getEncoded();
        return HexUtil.encodeHexStr(key);
    }

    public static String encodePassword(String password, String salt) {
        byte[] key = HexUtil.decodeHex(salt);
        // 使用HMAC SHA-256算法和密钥创建HMac对象
        HMac hMac = new HMac(HmacAlgorithm.HmacSHA256, key);

        // 对密码进行加密
        String hashedPassword = hMac.digestHex(password);
        return hashedPassword;
    }

    public static boolean checkPassword(String password, String encodedPassword, String salt) {
        byte[] key = HexUtil.decodeHex(salt);
        HMac hMac = new HMac(HmacAlgorithm.HmacSHA256, key);
        String hashedPassword = hMac.digestHex(password);

        return hashedPassword.equals(encodedPassword);
    }
}
