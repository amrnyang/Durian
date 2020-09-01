package com.swing.sky.oss.framework.jwt.service;

import com.swing.sky.oss.framework.datasource.redis.RedisUtils;
import com.swing.sky.oss.framework.jwt.util.JwtUtil;
import com.swing.sky.oss.module.domain.DurianUserDO;
import com.swing.sky.oss.module.service.DurianUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author JIANG
 * @since 2020-08-28
 */
@Component
public class JwtService {
    @Value("${token.secret}")
    private String secret;

    @Value("${token.expiredTime}")
    private int expiredTime;

    @Resource
    RedisUtils redisUtils;

    DurianUserService userService;

    @Autowired
    public void setUserService(DurianUserService userService) {
        this.userService = userService;
    }

    /**
     * 生成token，并将相关的用户信息存入redis
     *
     * @param username username
     * @return token
     */
    public String createToken(String username) {
        // 从数据库中得到用户
        DurianUserDO loginUser = userService.getUserByUsername(username);
        // 存入redis
        redisUtils.setObject(loginUser.getUsername(), loginUser, expiredTime, TimeUnit.HOURS);
        return JwtUtil.encode(username, secret, expiredTime);
    }



    /**
     * 刷新token和redis中的过期时间
     *
     * @param token 旧token
     * @return 新token
     */
    public String refreshToken(String token) throws JwtException {
        Claims claims = JwtUtil.parse(token, secret);
        long expiration = claims.getExpiration().getTime();
        long timeMillis = System.currentTimeMillis();
        // 过期时间小于1小时则刷新
        if (expiration - timeMillis < JwtUtil.HOUR) {
            String username = claims.getSubject();
            DurianUserDO user = redisUtils.getObject(username);
            redisUtils.deleteObjects(username);
            redisUtils.setObject(username, user, expiredTime, TimeUnit.HOURS);
            return JwtUtil.encode(username, secret, expiredTime);
        }
        return token;
    }

    /**
     * 刷新redis中的用户信息，并返回最新的token
     *
     * @param token token
     * @param user  最新的用户信息
     * @return 最新的token
     */
    public String refreshUser(String token, DurianUserDO user) throws JwtException {
        String username = JwtUtil.getSubject(token, secret);
        redisUtils.deleteObjects(username);
        redisUtils.setObject(username, user, expiredTime, TimeUnit.HOURS);
        return JwtUtil.encode(username, secret, expiredTime);
    }

    /**
     * 从redis中删除用户信息
     *
     * @param token token
     */
    public void deleteUser(String token) throws JwtException {
        String username = JwtUtil.getSubject(token, secret);
        redisUtils.deleteObjects(username);
    }

    /**
     * 从redis中获取token中用户的信息
     *
     * @param token token
     * @return 用户实体
     */
    public DurianUserDO getUserFromRedis(String token) throws JwtException {
        String username = JwtUtil.getSubject(token, secret);
        return redisUtils.getObject(username);
    }

    /**
     * 从token中获取用户名
     *
     * @param token token
     * @return 用户名
     */
    public String getUsername(String token) throws JwtException {
        return JwtUtil.getSubject(token, secret);
    }


    /**
     * 该用户是否存在于redis中
     *
     * @param username 用户名
     * @return true存在  false不存在
     */
    public boolean isUserInRedis(String username) throws JwtException {
        return redisUtils.getObject(username) != null;
    }
}
