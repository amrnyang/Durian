package com.swing.sky.system.framework.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swing.sky.system.framework.task.AsyncTaskService;
import com.swing.sky.system.framework.task.factory.AsyncTaskFactory;
import com.swing.sky.system.framework.web.SkyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author swing
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Resource
    private AsyncTaskFactory asyncTaskFactory;

    @Resource
    private AsyncTaskService asyncTaskService;

    public static final String JSON = "JSON";

    /**
     * 在application配置文件中配置登陆的类型是JSON数据响应还是做页面响应
     */
    @Value("${other.responseType}")
    private String responseType;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if (JSON.equalsIgnoreCase(responseType)) {
            //记录登录日志
            asyncTaskService.execute(asyncTaskFactory.recordLoginLog(true, "登录成功"));
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(OBJECT_MAPPER.writeValueAsString(SkyResponse.success("登录成功")));
        } else {
            // 会帮我们跳转到上一次请求的页面上
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}