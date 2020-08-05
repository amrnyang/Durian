package com.swing.sky.api.system.monitor;

import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author swing
 */
@Api
@Controller
@RequestMapping("monitor/swagger")
public class SwaggerController {
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('monitor:swagger:view')")
    public String swagger() {
        return "redirect:/doc.html";
    }
}
