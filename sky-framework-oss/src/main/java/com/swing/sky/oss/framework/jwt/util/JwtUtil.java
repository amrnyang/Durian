package com.swing.sky.oss.framework.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * @author JIANG
 * @since 2020-08-28
 */
public class JwtUtil {
    public static final long HOUR = 60 * 60 * 1000L;

    /**
     * jwt签证
     *
     * @param username    用户名（学号）
     * @param uuid        唯一身份标识符
     * @param secret      密钥
     * @param expiredTime 过期时间
     * @return jwt
     */
    public static String encode(String username, String uuid, String secret, int expiredTime) {
        long timeMillis = System.currentTimeMillis();
        // 生成密钥
        SecretKey key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        return Jwts.builder()
                // 唯一身份标识符(redis使用）
                .setId(uuid)
                // 用户名（学号）
                .setSubject(username)
                // 签发时间
                .setIssuedAt(new Date(timeMillis))
                // 过期时间
                .setExpiration(new Date(timeMillis + expiredTime * HOUR))
                .signWith(key)
                .compact();
    }

    /**
     * 检验和解析jwt
     *
     * @param jwt    jwt
     * @param secret 密钥
     * @return 数据实体
     * @throws JwtException token不合法
     */
    public static Claims parse(String jwt, String secret) throws JwtException {
        SecretKey key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 获取jwt唯一身份标识
     */
    public static String getId(String jwt, String secret) throws JwtException {
        return parse(jwt, secret).getId();
    }

    /**
     * 获取用户名（学号）
     */
    public static String getSubject(String jwt, String secret) throws JwtException {
        return parse(jwt, secret).getSubject();
    }

    /**
     * 获取jwt签发时间
     */
    public static Date getIssuedAt(String jwt, String secret) throws JwtException {
        return parse(jwt, secret).getIssuedAt();
    }

    /**
     * 获取jwt失效时间
     */
    public static Date getExpiration(String jwt, String secret) throws JwtException {
        return parse(jwt, secret).getExpiration();
    }
}
