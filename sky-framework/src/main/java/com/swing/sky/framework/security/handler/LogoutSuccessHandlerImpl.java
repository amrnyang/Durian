package com.swing.sky.framework.security.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类
 *
 * @author swing
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    /**
     * 当登录用户退出时，清除redis缓存中的数据,并记录注销记录
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.sendRedirect("/login");
    }
}
