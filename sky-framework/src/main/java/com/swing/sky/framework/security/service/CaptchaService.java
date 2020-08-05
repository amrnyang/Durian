package com.swing.sky.framework.security.service;

import com.swing.sky.common.constant.CaptchaConstants;
import com.swing.sky.framework.web.utils.ServletUtils;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;

/**
 * 验证码服务
 *
 * @author swing
 */
@Service
public class CaptchaService {

    @Value("${captcha.folderName}")
    private String folderName;
    @Value("${captcha.captchaExpiration}")
    private Integer captchaExpiration;

    public SpecCaptcha getSpecCaptcha(String captchaUuid) throws IOException, FontFormatException {
        // 生成验证码
        SpecCaptcha specCaptcha = new SpecCaptcha(111, 36, 3);
        //设置字体
        specCaptcha.setFont(Captcha.FONT_5);
        //获取验证码的值
        String captchaCodeValue = specCaptcha.text().toLowerCase();
        //将value放入session中
        ServletUtils.setSessionAttribute(CaptchaConstants.CAPTCHA, captchaCodeValue);
        //验证码有效时间设置
        ServletUtils.getRequest().getSession().setMaxInactiveInterval(captchaExpiration*60);
        return specCaptcha;
    }

    public String getFolderName() {
        return folderName;
    }
}
