package com.su.blog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Map;

public class JwtUtil {

    /**
     * 生成jwt
     * 使用Hs256算法, 私匙使用固定秘钥
     *
     */
    //生成Jwt
    public static String createJwt(String key, long expirationTime, Map<String, Object> claims) {
        //签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //有效时间
        long Millis = System.currentTimeMillis() + expirationTime;
        Date expiration = new Date(Millis);
        JwtBuilder jwtBuilder =
                Jwts.builder()
                        .setClaims(claims)
                        .signWith(signatureAlgorithm, key.getBytes(StandardCharsets.UTF_8))
                        .setExpiration(expiration);
        return jwtBuilder.compact();
    }


    //解密验证Token
    public static Claims parseJWT(String key, String token) {
        // 得到DefaultJwtParser
        Claims claims = Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(key.getBytes(StandardCharsets.UTF_8))
                // 设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }
}

