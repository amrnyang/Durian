//package com.swing.sky.framework.security.service;
//
//import com.swing.sky.common.constant.CaptchaConstants;
//import com.swing.sky.framework.web.utils.ServletUtils;
//import com.swing.sky.framework.datasource.redis.RedisUtils;
//import com.swing.sky.framework.task.AsyncTaskService;
//import com.swing.sky.framework.task.factory.AsyncTaskFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
///**
// * 登录校验方法
// *
// * @author swing
// */
//@Service
//public class LoginService {
//    @Resource
//    private JwtService jwtService;
//
//    @Resource
//    private RedisUtils redisUtils;
//
//    @Resource
//    private CaptchaService captchaService;
//
//    @Resource
//    private AsyncTaskFactory asyncTaskFactory;
//
//    @Resource
//    private AsyncTaskService asyncTaskService;
//
//    /**
//     * 权限验证中心，登录在此验证
//     */
//    @Resource
//    private AuthenticationManager authenticationManager;
//
//
//    /**
//     * 登录验证
//     *
//     * @param username 用户名
//     * @param password 密码
//     * @param code     验证码
//     * @return token
//     */
//    public String login(String username, String password, String code) {
//        //从session中获取验证码的uuid，然后删除session
//        String uuid = (String) ServletUtils.getSessionAttribute(CaptchaConstants.CAPTCHA_UUID);
//        ServletUtils.setSessionAttribute(CaptchaConstants.CAPTCHA_UUID, null);
//        //检查验证码
//        String captchaCodeKey = captchaService.getFolderName() + ":" + uuid;
//        String captchaCodeValue = redisUtils.getObject(captchaCodeKey);
//        //使用完后删除
//        redisUtils.deleteObjects(captchaCodeKey);
//        if (captchaCodeValue == null) {
//            asyncTaskService.execute(asyncTaskFactory.recordLoginLog(username, false, "验证码失效"));
//            throw new RuntimeException("验证码失效");
//        }
//        if (!code.equalsIgnoreCase(captchaCodeValue)) {
//            asyncTaskService.execute(asyncTaskFactory.recordLoginLog(username, false, "验证码错误"));
//            throw new RuntimeException("验证码错误");
//        }
//        Authentication authentication;
//        try {
//            //从认证中心获取用户认证结果
//            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (Exception e) {
//            if (e instanceof BadCredentialsException) {
//                asyncTaskService.execute(asyncTaskFactory.recordLoginLog(username, false, "密码错误"));
//                throw new BadCredentialsException("用户密码错误");
//            } else {
//                asyncTaskService.execute(asyncTaskFactory.recordLoginLog(username, false, "认证错误"));
//                throw new RuntimeException("认证错误");
//            }
//        }
//        //记录登录成功消息
//        asyncTaskService.execute(asyncTaskFactory.recordLoginLog(username, true, "登录成功"));
//        //获取成功登录用户的的信息
//        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
//        // 生成token
//        return jwtService.createToken(userDetail);
//    }
//}
