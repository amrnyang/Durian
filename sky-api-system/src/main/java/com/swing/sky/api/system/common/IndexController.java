package com.swing.sky.api.system.common;

import com.swing.sky.common.basic.BasicDO;
import com.swing.sky.framework.security.utils.UserDetailsUtil;
import com.swing.sky.api.system.dto.response.BuildThymeleafMenu;
import com.swing.sky.api.system.dto.response.menu.ThymeleafMenu;
import com.swing.sky.module.system.domain.SysMenuDO;
import com.swing.sky.module.system.domain.SysUserDO;
import com.swing.sky.module.system.service.SysMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author swing
 */
@Api
@Controller
public class IndexController {
    @Autowired
    private SysMenuService menuService;

    /**
     * 获取首页
     *
     * @return 首页
     */
    @GetMapping(value = {"/index", "/"})
    public String index(Model model) {
        SysMenuDO menu = new SysMenuDO();
        menu.setUse(true);
        //获取当前用户id
        List<SysMenuDO> menuDOList = menuService.listByConditionAndUserId(UserDetailsUtil.getUserId(), menu, null, null);
        List<ThymeleafMenu> thymeleafMenus = BuildThymeleafMenu.build(0L, menuDOList);
        List<ThymeleafMenu> thymeleafMenusOrder = thymeleafMenus.stream().sorted(Comparator.comparingInt(BasicDO::getOrderNum)).collect(Collectors.toList());
        SysUserDO user = UserDetailsUtil.getUserDO();
        model.addAttribute("menus", thymeleafMenusOrder);
        model.addAttribute("user", user);
        model.addAttribute("copyrightYear", "2020");
        model.addAttribute("demoEnabled", true);
        return "index";
    }

    /**
     * 系统介绍
     *
     * @param model 模型
     * @return 首页
     */
    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("version", "1.0.2");
        return "main";
    }

    /**
     * 切换主题,主题配置信息存放在cookie的本地存储中
     */
    @GetMapping("/system/switchSkin")
    public String switchSkin() {
        return "skin";
    }

}
