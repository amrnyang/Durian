package com.swing.sky.oss.api.common;

import com.swing.sky.common.utils.StringUtils;
import com.swing.sky.common.utils.security.TokenUtils;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.oss.framework.jwt.service.JwtService;
import com.swing.sky.oss.module.domain.DurianUserDO;
import com.swing.sky.oss.module.service.DurianUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author JIANG
 * @since 2020-08-28
 */
@RestController
public class LoginController {
    @Resource
    private JwtService jwtService;

    private DurianUserService userService;

    @Autowired
    public void setUserService(DurianUserService userService) {
        this.userService = userService;
    }

    /**
     * 登陆接口，登陆成功返回jwt
     */
    @GetMapping("/login")
    public SkyResponse login(String username, String password) {

        DurianUserDO loginUser = userService.getUserByUsername(username);
        if (loginUser == null) {
            throw new RuntimeException("用户不存在，请重新输入");
        }
        if (!new BCryptPasswordEncoder().matches(password, loginUser.getPassword())) {
            throw new RuntimeException("密码不正确，请重新输入");
        }
        if (jwtService.isUserInRedis(username)) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "该用户已登陆，请不要重复登陆！");
        }
        String newToken = jwtService.createToken(username);
        return SkyResponse.success("登陆成功", 1)
                .put("token", newToken);
    }

    /**
     * 注销登陆接口
     */
    @PostMapping("/logout")
    public SkyResponse logout(HttpServletRequest request) {
        String token = TokenUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            jwtService.deleteUser(token);
            return SkyResponse.success("登出成功");
        }
        return SkyResponse.fail(HttpStatus.NOT_ACCEPTABLE, "当前还未登陆");
    }
}
