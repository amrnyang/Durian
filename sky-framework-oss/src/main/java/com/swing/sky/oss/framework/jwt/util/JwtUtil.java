package com.swing.sky.oss.framework.jwt.util;

import io.jsonwebtoken.*;

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
     * 检验jwt是否过期（其实如果jwt过期的话，就取不出任何数据，并抛出ExpiredJwtException）
     *
     * @param jwt    jwt
     * @param secret 密钥
     * @return true过期  false未过期
     * @throws ExpiredJwtException 令牌过期
     */
    public static boolean isExpired(String jwt, String secret) throws ExpiredJwtException {
        Date expiration = getExpiration(jwt, secret);
        return expiration.before(new Date());
    }

    /**
     * 获取jwt唯一身份标识
     */
    public static String getId(String jwt, String secret) {
        return parse(jwt, secret).getId();
    }

    /**
     * 获取用户名（学号）
     */
    public static String getSubject(String jwt, String secret) {
        return parse(jwt, secret).getSubject();
    }

    /**
     * 获取jwt签发时间
     */
    public static Date getIssuedAt(String jwt, String secret) {
        return parse(jwt, secret).getIssuedAt();
    }

    /**
     * 获取jwt失效时间
     */
    public static Date getExpiration(String jwt, String secret) {
        return parse(jwt, secret).getExpiration();
    }

    public static void main(String[] args) {
        String secret = "skyjwtutil_skycontrol_9258058494";
        Claims parse = parse("eyJhbGciOiJIUzI1NiJ9." +
                "eyJqdGkiOiI2NWQ3MzE0MTE2OTI0MD" +
                "k2YTFiNDA0MmQ5YmNiZWQ2NCIsInN1Yi" +
                "I6IjEyMzQ1NjciLCJpYXQiOjE1OTg2ODgxN" +
                "TQsImV4cCI6MTU5ODk0NzM1NH0." +
                "74KN2vl8cyMKial3h5xtsj3hBgUwvcqRGPLI3mtjaaA", secret);
        System.out.println(parse.getId());
    }
}
