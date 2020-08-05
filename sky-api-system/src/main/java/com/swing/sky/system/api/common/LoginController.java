package com.swing.sky.system.api.common;


import com.swing.sky.system.framework.security.utils.UserDetailsUtil;
import com.swing.sky.system.framework.web.SkyResponse;
import com.swing.sky.system.module.domain.SysUserDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录验证
 *
 * @author swing
 */
@Api
@Controller
public class LoginController {

    /**
     * 获取登录页面
     *
     * @return 登录页面
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    /**
     * 获取登录用户的信息
     *
     * @return 用户信息
     */
    @GetMapping("/login-info")
    @ApiOperation(value = "获取登录用户的信息")
    @ResponseBody
    public SkyResponse getInfo() {
        SysUserDO user = UserDetailsUtil.getUserDO();
        return SkyResponse.success(1)
                .put("user", user);
    }
}
