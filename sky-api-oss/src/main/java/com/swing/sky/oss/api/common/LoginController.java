package com.swing.sky.oss.api.common;

import com.swing.sky.common.utils.StringUtils;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.oss.framework.jwt.service.JwtService;
import com.swing.sky.oss.module.domain.DurianUserDO;
import com.swing.sky.oss.module.service.DurianUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/login")
    public SkyResponse login(String username, String password) {
        DurianUserDO loginUser = userService.getUserByUsername(username);
        if (loginUser == null) {
            throw new RuntimeException("用户不存在，请重新输入");
        }
        if (!new BCryptPasswordEncoder().matches(password, loginUser.getPassword())) {
            throw new RuntimeException("密码不正确，请重新输入");
        }
        String token = jwtService.createToken(username);
        return SkyResponse.success("登陆成功",1)
                .put("token", token);
    }

    @GetMapping("/login-info")
    public SkyResponse getInfo(HttpServletRequest request) {
        String token = jwtService.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            // 检验是否无效、是否过期，无效和过期都抛出异常
            DurianUserDO user = jwtService.getLoginUser(token);
            if (user != null) {
                // 刷新token和redis中的过期时间，老token只有没过期依旧可以请求数据
                String newToken = jwtService.refreshToken(token);
                return SkyResponse.success(2)
                        .put("user", user)
                        .put("token", newToken);
            }
        }
        // 请求头中无token或redis无用户（已登出）
        return SkyResponse.fail(HttpStatus.NOT_ACCEPTABLE, "当前还未登陆");
    }

    @GetMapping("/logout")
    public SkyResponse logout(HttpServletRequest request) {
        String token = jwtService.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            jwtService.deleteUser(token);
            return SkyResponse.success("登出成功");
        }
        return SkyResponse.fail(HttpStatus.NOT_ACCEPTABLE, "当前还未登陆");
    }
}
