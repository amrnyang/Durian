package com.swing.sky.system.api.es;

import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.es.inner.EsDocService;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * solr数据服务接口
 *
 * @author swing
 */
@Controller
@RequestMapping("es/dataServer")
public class EsController {
    @Resource
    EsDocService esDocService;

    /**
     * 获取数据服务页面
     */
    @GetMapping
    @PreAuthorize("@sca.needAuthoritySign('es:dataServer')")
    public String dataServer() {
        return "es/dataServer";
    }

    @PostMapping("/tiku/update-doc")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('es:dataServer')")
    public SkyResponse updateTiku(String key) {
        return esDocService.updateTikuEsDoc(key);
    }
}
