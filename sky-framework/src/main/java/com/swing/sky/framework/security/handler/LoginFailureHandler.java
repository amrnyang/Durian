package com.swing.sky.framework.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swing.sky.framework.task.AsyncTaskService;
import com.swing.sky.framework.task.factory.AsyncTaskFactory;
import com.swing.sky.framework.web.SkyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
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
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
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
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //记录登录日志
        asyncTaskService.execute(asyncTaskFactory.recordLoginLog(false, "认证失败"));
        if (JSON.equalsIgnoreCase(responseType)) {
            response.setContentType("application/json;charset=UTF-8");
            //用户信息未找到异常
            response.getWriter().write(OBJECT_MAPPER.writeValueAsString(SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage())));
        } else {
            response.setContentType("text/html;charset=UTF-8");
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}
