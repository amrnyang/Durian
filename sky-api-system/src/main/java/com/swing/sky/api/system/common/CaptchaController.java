package com.swing.sky.api.system.common;

import com.alibaba.fastjson.JSON;
import com.swing.sky.framework.web.utils.ServletUtils;
import com.swing.sky.common.utils.UUIDUtils;
import com.swing.sky.framework.security.service.CaptchaService;
import com.swing.sky.framework.web.SkyResponse;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

/**
 * 验证码请求接口
 *
 * @author swing
 */
@Api
@Controller
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;

    /**
     * 生成验证码
     */
    @ApiOperation(value = "获取验证码")
    @GetMapping("/captcha")
    public ModelAndView getCode(HttpServletResponse response) throws IOException, FontFormatException {
        //生成在redis存储验证码的键
        String captchaUuid = UUIDUtils.getUuid();
        SpecCaptcha specCaptcha = captchaService.getSpecCaptcha(captchaUuid);

        ServletOutputStream out = null;
        try {
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");
            out = response.getOutputStream();
            specCaptcha.out(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            ServletUtils.renderString(JSON.toJSONString(SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())));
        } finally {
            assert out != null;
            out.close();
        }
        return null;
    }
}
