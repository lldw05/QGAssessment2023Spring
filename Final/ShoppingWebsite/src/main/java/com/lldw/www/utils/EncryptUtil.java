package com.lldw.www.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author
 * @date
 */
public class EncryptUtil {

    public static String encrypt(String str) {
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest = md5.digest(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //System.out.println(Arrays.toString(digest));
        //16是表示转换为16进制数
        String md5Str = new BigInteger(1, digest).toString(16);
        System.out.println("加密成功!");
        return md5Str;
    }

}
