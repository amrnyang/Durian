package com.swing.sky.system.api.solr;

import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * solr数据服务接口
 *
 * @author swing
 */
@Api
@Controller
@RequestMapping("solr/dataServer")
public class SolrController {
    /**
     * 获取数据服务页面
     */
    @GetMapping
    @PreAuthorize("@sca.needAuthoritySign('solr:dataServer')")
    public String dataServer() {
        return "solr/dataServer";
    }

}
