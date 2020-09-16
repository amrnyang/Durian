package com.swing.sky.es.inner;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.swing.sky.common.web.SkyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 对内提供服务间的请求（暂使用REST，后续微服务版本会使用RPC通信框架替代）
 *
 * @author swing
 */
@Service
public class EsDocService {
    /**
     * 搜素引擎服务部署地址
     */
    @Value("${innerService.es.url}")
    private String url;

    public SkyResponse updateTikuEsDoc(String key) {
        //使用该token去请求单点登录中心，获取用户登录信息
        String body = HttpRequest.post(url + "/es/search/tiku/question/update-doc?key=" + key)
                .timeout(20000)
                .execute().body();
        if (body == null) {
            throw new RuntimeException("内部服务间请求出错");
        }
        return JSON.parseObject(body, SkyResponse.class);
    }
}
