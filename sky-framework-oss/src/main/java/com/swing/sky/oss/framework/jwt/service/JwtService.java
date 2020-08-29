package com.swing.sky.oss.framework.jwt.service;

import com.swing.sky.common.utils.StringUtils;
import com.swing.sky.common.utils.UUIDUtils;
import com.swing.sky.oss.framework.datasource.redis.RedisUtils;
import com.swing.sky.oss.framework.jwt.util.JwtUtil;
import com.swing.sky.oss.module.domain.DurianUserDO;
import com.swing.sky.oss.module.service.DurianUserService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

import static com.swing.sky.oss.framework.constant.HttpConstant.TOKEN_HEADER_NAME;
import static com.swing.sky.oss.framework.constant.HttpConstant.TOKEN_PREFIX_NAME;

/**
 * @author JIANG
 * @since 2020-08-28
 */
@Component
public class JwtService {
    public static final Logger log = LoggerFactory.getLogger(JwtService.class);

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
        // 生成唯一的用户标识符
        String uuid = UUIDUtils.getUuid();
        // 从数据库中得到用户
        DurianUserDO loginUser = userService.getUserByUsername(username);
        // 存入redis
        redisUtils.setObject(uuid, loginUser, expiredTime, TimeUnit.HOURS);
        return JwtUtil.encode(username, uuid, secret, expiredTime);
    }

    /**
     * 刷新token和redis中的过期时间
     *
     * @param token 旧token
     * @return 新token
     */
    public String refreshToken(String token) {
        Claims claims = JwtUtil.parse(token, secret);
        long expiration = claims.getExpiration().getTime();
        long timeMillis = System.currentTimeMillis();
        // 过期时间小于1小时则刷新
        if (expiration - timeMillis < JwtUtil.HOUR) {
            String uuid = claims.getId();
            DurianUserDO user = (DurianUserDO)redisUtils.getObject(uuid);
            redisUtils.deleteObjects(uuid);
            redisUtils.setObject(uuid, user, expiredTime, TimeUnit.HOURS);
            return JwtUtil.encode(claims.getSubject(), uuid, secret, expiredTime);
        }
        return token;
    }

    /**
     * 从redis中获取token中用户的信息
     *
     * @param token token
     * @return 用户实体
     */
    public DurianUserDO getLoginUser(String token) {
        String uuid = JwtUtil.getId(token, secret);
        return redisUtils.getObject(uuid);
    }

    /**
     * 从请求头中得到token
     *
     * @param request 请求
     * @return 如果有token，则返回token，无返回null
     */
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER_NAME);
        if (StringUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX_NAME)) {
            token = token.replace(TOKEN_PREFIX_NAME, "");
            log.info("该用户token为{}", token);
            return token;
        }
        return null;
    }
}
