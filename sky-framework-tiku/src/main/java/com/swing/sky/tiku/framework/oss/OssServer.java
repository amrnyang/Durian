package com.swing.sky.tiku.framework.oss;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.swing.sky.common.utils.security.TokenUtils;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.oss.module.domain.DurianUserDO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 该模块的单点登录工具
 *
 * @author swing
 * @since 2020-8-31
 */
@Service
public class OssServer {
    /**
     * 单点登录中心地址
     */
    @Value("${oss.url}")
    private String url;

    public DurianUserDO getLoginInfo(HttpServletRequest request) {
        //获取请求携带的token
        String token = TokenUtils.getToken(request);
        if (token == null) {
            throw new RuntimeException("未检测到请求携带的token");
        }
        //使用该token去请求单点登录中心，获取用户登录信息
        String body = HttpRequest.get(url + "/login-info")
                .header("Authorization", "Bearer " + token)
                .timeout(20000)
                .execute().body();
        if (body == null) {
            throw new RuntimeException("请求单点登录中心出错");
        }
        SkyResponse skyResponse = JSON.parseObject(body, SkyResponse.class);
        if (skyResponse.getStatus().equals(HttpStatus.HTTP_NOT_ACCEPTABLE)) {
            throw new RuntimeException("该用户还未登录");
        }
        if (skyResponse.getStatus().equals(HttpStatus.HTTP_INTERNAL_ERROR)) {
            throw new RuntimeException("令牌无效");
        }
        return JSON.parseObject(skyResponse.getBody().get("user").toString(), DurianUserDO.class);
    }
}
