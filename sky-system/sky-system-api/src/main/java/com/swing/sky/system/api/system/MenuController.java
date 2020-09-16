package com.swing.sky.system.api.system;

import com.swing.sky.system.module.annotation.OperateLog;
import com.swing.sky.system.module.constants.BusinessTypeConstants;
import com.swing.sky.system.module.constants.ModuleConstants;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.BuildUtils;
import com.swing.sky.system.dto.response.tree.TreeDTO;
import com.swing.sky.system.framework.security.utils.UserDetailsUtil;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.system.module.domain.SysMenuDO;
import com.swing.sky.system.module.service.SysMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author swing
 */
@Api
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BasicController {
    private SysMenuService menuService;

    @Autowired
    public void setMenuService(SysMenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('system:menu:view')")
    public String menu() {
        return "system/menu/menu";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:menu:list')")
    public List<SysMenuDO> list(SysMenuDO menu) {
        return menuService.listByConditionAndUserId(UserDetailsUtil.getUserId(), menu, null, null);
    }

    /**
     * 新增（视图）
     */
    @GetMapping("/add/{parentId}")
    @PreAuthorize("@sca.needAuthoritySign('system:menu:add')")
    public String add(@PathVariable("parentId") Long parentId, Model model) {
        SysMenuDO menu = null;
        if (0L != parentId) {
            menu = menuService.getById(parentId);
        } else {
            menu = new SysMenuDO();
            menu.setId(0L);
            menu.setMenuName("主目录");
        }
        model.addAttribute("menu", menu);
        return "system/menu/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:menu:add')")
    @OperateLog(module = ModuleConstants.MENU,businessType = BusinessTypeConstants.INSERT)
    public SkyResponse addSave(@Validated SysMenuDO menu) {
        menuService.insert(menu);
        return SkyResponse.success("新增菜单信息成功！");
    }

    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{menuId}")
    @PreAuthorize("@sca.needAuthoritySign('system:menu:edit')")
    public String edit(@PathVariable("menuId") Long menuId, Model model) {
        model.addAttribute("menu", menuService.getById(menuId));
        Long parentId = menuService.getById(menuId).getParentId();
        String parentName = parentId == 0L ? null : menuService.getById(parentId).getMenuName();
        model.addAttribute("parentName", parentName);
        return "system/menu/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:menu:edit')")
    @OperateLog(module = ModuleConstants.MENU,businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse editSave(@Validated SysMenuDO menu) {
        menuService.update(menu);
        return SkyResponse.success("更新菜单信息成功！");
    }

    /**
     * 删除
     */
    @GetMapping("/remove/{menuId}")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:menu:remove')")
    @OperateLog(module = ModuleConstants.MENU,businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(@PathVariable("menuId") Long menuId) {
        menuService.deleteById(menuId);
        return SkyResponse.success("菜单删除成功！");
    }

    /**
     * 选择菜单图标（视图）
     */
    @GetMapping("/icon")
    @PreAuthorize("@sca.needAuthoritySign('system:menu:view')")
    public String icon() {
        return "system/menu/icon";
    }

    /**
     * 菜单选择树(单选）
     */
    @GetMapping("/menuRadioTreeView/{menuId}")
    @PreAuthorize("@sca.needAuthoritySign('system:menu:view')")
    public String selectMenuTree(@PathVariable("menuId") Long menuId, Model model) {
        model.addAttribute("menu", menuService.getById(menuId));
        return "system/menu/tree";
    }

    /**
     * 加载所有菜单列表树（展示使用）
     */
    @GetMapping("/menuRadioTree")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:menu:view')")
    public List<TreeDTO> menuTreeData() {
        List<SysMenuDO> menuDOList = menuService.listByConditionAndUserId(UserDetailsUtil.getUserId(), null, null, null);
        return BuildUtils.buildMenuSelectTree(menuDOList);
    }
}
