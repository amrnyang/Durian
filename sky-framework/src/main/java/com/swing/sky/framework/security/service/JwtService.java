//package com.swing.sky.framework.security.service;
//
//import com.swing.sky.common.constant.SessionConstants;
//import com.swing.sky.framework.web.utils.ServletUtils;
//import com.swing.sky.common.utils.UUIDUtils;
//import com.swing.sky.framework.datasource.redis.RedisUtils;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import javax.crypto.SecretKey;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
///**
// * token相关的服务
// *
// * @author swing
// */
//@Service
//public class JwtService {
//    @Value("${jwt.header}")
//    private String header;
//
//    @Value("${jwt.tokenPrefix}")
//    private String tokenPrefix;
//
//    @Value("${jwt.expireTime}")
//    private int expireTime;
//
//    @Value("${jwt.folderName}")
//    private String folderName;
//
//    @Resource
//    private RedisUtils redisUtils;
//
//    /**
//     * 密钥
//     */
//    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//
//    /**
//     * 创建token,并将用户的信息放入redis
//     * token的有效期和redis中的用户信息有效期相同
//     *
//     * @param userDetails 用户信息
//     * @return 令牌
//     */
//    public String createToken(UserDetails userDetails) {
//        //存储在线用户信息的文件夹名
//        String uuid = UUIDUtils.getUuid();
//        String userDetailsKey = folderName + ":" + uuid;
//        //将用户信息放入缓存
//        redisUtils.setObject(userDetailsKey, userDetails, expireTime, TimeUnit.MINUTES);
//        //设置令牌存储的信息内容
//        Map<String, Object> claims = new HashMap<>(2);
//        claims.put("uuid", uuid);
//        //创建令牌
//        return Jwts
//                .builder()
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis() + expireTime * 60 * 1000))
//                .signWith(SECRET_KEY)
//                .compact();
//    }
//
//    /**
//     * 解析token
//     *
//     * @return token中的信息
//     */
//    public Map<String, Object> resolverToken() {
//        String token = (String) ServletUtils.getSessionAttribute(SessionConstants.TOKEN);
//        if (token != null) {
//            //解析token
//            return Jwts.parserBuilder()
//                    .setSigningKey(SECRET_KEY)
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//        }
//        return null;
//    }
//
//
//    /**
//     * 根据请求获取UserDetailsImpl
//     *
//     * @return UserDetailsImpl
//     */
//    public UserDetailsImpl getUserDetailImplByRequest() {
//        Map<String, Object> map = resolverToken();
//        if (map != null) {
//            String uuid = (String) map.get("uuid");
//            String userDetailsKey = folderName + ":" + uuid;
//            return redisUtils.getObject(userDetailsKey);
//        }
//        return null;
//    }
//
//    /**
//     * 根据请求删除UserDetailsImpl
//     */
//    public void deleteUserDetailImplByRequest() {
//        Map<String, Object> map = resolverToken();
//        if (map != null) {
//            String uuid = (String) map.get("uuid");
//            String userDetailsKey = folderName + ":" + uuid;
//            redisUtils.deleteObjects(userDetailsKey);
//        }
//    }
//
//    /**
//     * 根据请求获取token
//     *
//     * @param request 请求
//     * @return token
//     */
//    public String getTokenByRequest(HttpServletRequest request) {
//        return request.getHeader(header);
//    }
//
//    /**
//     * 获取当前请求用户的id
//     *
//     * @return id
//     */
//    public Long getUserId() {
//        return getUserDetailImplByRequest().getUserDO().getId();
//    }
//
//    /**
//     * 获取当前请求用户的用户名
//     *
//     * @return 用户名
//     */
//    public String getUserName() {
//        return getUserDetailImplByRequest().getUserDO().getUsername();
//    }
//}