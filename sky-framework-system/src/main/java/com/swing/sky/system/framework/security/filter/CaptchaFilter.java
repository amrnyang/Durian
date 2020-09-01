package com.swing.sky.system.framework.security.filter;

import com.alibaba.fastjson.JSON;
import com.swing.sky.common.constant.CaptchaConstants;
import com.swing.sky.system.framework.task.AsyncTaskService;
import com.swing.sky.system.framework.task.factory.AsyncTaskFactory;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.common.utils.ServletUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证登录请求中的验证码是否正确
 *
 * @author swing
 */
@Component
public class CaptchaFilter extends GenericFilterBean {

    @Resource
    private AsyncTaskFactory asyncTaskFactory;

    @Resource
    private AsyncTaskService asyncTaskService;

    private static final String LOGIN_URL = "/login";

    private static final String LOGIN_METHOD = "post";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getRequestURL().toString().endsWith(LOGIN_URL) && request.getMethod().equalsIgnoreCase(LOGIN_METHOD)) {
            String captcha = request.getParameter("captcha");
            //从session中获取验证码的uuid，然后删除该值
            String captchaCodeValue = (String) ServletUtils.getSessionAttribute(CaptchaConstants.CAPTCHA);
            ServletUtils.setSessionAttribute(CaptchaConstants.CAPTCHA, null);
            if (captchaCodeValue == null) {
                asyncTaskService.execute(asyncTaskFactory.recordLoginLog(false, "验证码失效"));
                ServletUtils.renderString(JSON.toJSONString(SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "验证码失效")));
                return;
            }
            if (!captcha.equalsIgnoreCase(captchaCodeValue)) {
                asyncTaskService.execute(asyncTaskFactory.recordLoginLog(false, "验证码错误"));
                ServletUtils.renderString(JSON.toJSONString(SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "验证码错误")));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
