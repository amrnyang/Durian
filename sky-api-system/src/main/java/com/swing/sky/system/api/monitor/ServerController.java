package com.swing.sky.system.api.monitor;

import com.swing.sky.system.module.domain.server.Server;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务器信息监控
 *
 * @author swing
 */
@Api
@Controller
@RequestMapping("monitor/server")
public class ServerController {
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('monitor:server:view')")
    public String swagger(Model model) throws Exception {
        Server server = new Server();
        server.copyTo();
        model.addAttribute("server", server);
        return "monitor/server/server";
    }
}
