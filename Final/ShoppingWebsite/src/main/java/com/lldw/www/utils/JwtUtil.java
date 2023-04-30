package com.lldw.www.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;

/**
 * @author lldw
 * @date 2023-04-26 15:04:11
 */
public class JwtUtil {
    private static String SIGN;
    private static int TIME;

    static {
        try {
            //new对象
            Properties pro = new Properties();

            //读取配置 properties文件与src同级
            pro.load(new FileReader(new File("src/main/resources/JwtSign.properties")));
            SIGN = pro.getProperty("SIGN");
            TIME = Integer.parseInt(pro.getProperty("TIME"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成Token
     */
    public static String getToken(Map<String, Object> map) {
        JWTCreator.Builder builder = JWT.create();
        Calendar instance = Calendar.getInstance();
        /**
         * 过期时间默认为7七天
         */
        instance.add(Calendar.HOUR, TIME);
        map.forEach((k, v) -> {
            builder.withClaim(k, v.toString());
        });
        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * 验证token并获取token中payload
     */
    public static DecodedJWT decode(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    public static <T> T getObjectFromToken(Class<T> clazz,String token, String name ) {
        return JwtUtil.decode(token).getClaim(name).as(clazz);
    }

}
