package com.swing.sky.center.inner;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.swing.sky.center.module.domain.CenterUserDO;
import com.swing.sky.common.utils.security.TokenUtils;
import com.swing.sky.common.web.SkyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 对内提供服务间的请求（暂使用REST，后续微服务版本会使用RPC通信框架替代）
 *
 * @author swing
 */
@Service
public class UserRequestService {
    /**
     * 信息中心服务部署地址
     */
    @Value("${innerService.center.url}")
    private String url;

    /**
     * 获取用户信息接口
     *
     * @param request 请求
     * @return 用户信息
     */
    public CenterUserDO getLoginInfo(HttpServletRequest request) {
        //获取请求携带的token
        String token = TokenUtils.getToken(request);
        if (token == null) {
            throw new RuntimeException("未检测到请求携带的token");
        }
        //使用该token去请求单点登录中心，获取用户登录信息
        String body = HttpRequest.get(url + "/profile/get-info")
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
        return JSON.parseObject(skyResponse.getBody().get("user").toString(), CenterUserDO.class);
    }
}
