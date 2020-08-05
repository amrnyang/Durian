package com.swing.sky.system.api.monitor;

import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 数据库监控
 * @author swing
 */
@Api
@Controller
@RequestMapping("monitor/database")
public class DatabaseController {
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('monitor:database:view')")
    public String swagger() {
        return "redirect:/druid/index.html";
    }
}
