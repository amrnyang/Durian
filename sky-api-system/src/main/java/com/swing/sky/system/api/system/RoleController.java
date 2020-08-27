package com.swing.sky.system.api.system;

import cn.hutool.core.convert.Convert;
import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.BuildUtils;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.system.dto.response.tree.TreeDTO;
import com.swing.sky.system.framework.excel.util.ExcelUtils;
import com.swing.sky.system.framework.security.utils.UserDetailsUtil;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.system.module.domain.SysDeptDO;
import com.swing.sky.system.module.domain.SysMenuDO;
import com.swing.sky.system.module.domain.SysRoleDO;
import com.swing.sky.system.module.service.SysDeptService;
import com.swing.sky.system.module.service.SysMenuService;
import com.swing.sky.system.module.service.SysRoleService;
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
@RequestMapping("system/role")
public class RoleController extends BasicController {
    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysDeptService deptService;


    /**
     * 主界面（视图)
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('system:role:view')")
    public String role() {
        return "system/role/role";
    }


    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:role:list')")
    public TableDataInfo list(SysRoleDO role) {
        startPage();
        List<SysRoleDO> list = roleService.listByConditionAndUserId(UserDetailsUtil.getUserId(), role, null, null);
        return buildDataTable(list);
    }

    /**
     * 新增（视图）
     */
    @GetMapping("/add")
    @PreAuthorize("@sca.needAuthoritySign('system:role:add')")
    public String add() {
        return "system/role/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:role:add')")
    @OperateLog(module = ModuleConstants.ROLE, businessType = BusinessTypeConstants.INSERT)
    public SkyResponse addSave(@Validated SysRoleDO roleDO) {
        roleService.insert(roleDO);
        return SkyResponse.success("角色信息插入成功！");
    }

    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{roleId}")
    @PreAuthorize("@sca.needAuthoritySign('system:role:edit')")
    public String edit(@PathVariable("roleId") Long roleId, Model model) {
        model.addAttribute("role", roleService.getById(roleId));
        return "system/role/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:role:edit')")
    @OperateLog(module = ModuleConstants.ROLE, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse editSave(@Validated SysRoleDO role) {
        roleService.update(role);
        return SkyResponse.success("角色信息更新成功！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:role:remove')")
    @OperateLog(module = ModuleConstants.ROLE, businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(String ids) {
        Long[] roleIds = Convert.toLongArray(ids);
        roleService.batchDeleteByIds(roleIds);
        return SkyResponse.success("角色信息删除成功！");
    }

    /**
     * 角色部门权限视图获取（视图)
     */
    @GetMapping("/deptLink/{roleId}")
    @PreAuthorize("@sca.needAuthoritySign('system:dept:list')")
    public String authDept(@PathVariable("roleId") Long roleId, Model model) {
        model.addAttribute("roleId", roleId);
        return "system/role/deptLink";
    }

    /**
     * 获取角色部门权限树（已选择）（视图调用）
     */

    @GetMapping("/deptMultipleTree/{roleId}")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:role:view')")
    public List<TreeDTO> deptSelectedTree(@PathVariable("roleId") Long roleId) {
        //获取该用户所有的可支配的部门信息
        List<SysDeptDO> deptDOList = deptService.listByConditionAndUserId(UserDetailsUtil.getUserId(), null, null, null);
        //获取该角色所有的关联部门（作为选中的依据）
        Long[] checkIds = roleService.listDeptIdsByRoleId(roleId);
        return BuildUtils.buildDeptSelectedTree(deptDOList, checkIds);
    }

    /**
     * 保存部门角色关联
     */

    @PostMapping("/updateDeptLink")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:role:edit')")
    @OperateLog(module = ModuleConstants.ROLE, businessType = BusinessTypeConstants.GRANT)
    public SkyResponse authDeptSave(Long roleId, String deptIds) {
        Long[] deptIds2 = Convert.toLongArray(deptIds);
        roleService.updateRoleDeptLink(roleId, deptIds2);
        return SkyResponse.success("角色部门关联信息插入成功！");
    }

    /**
     * 角色菜单权限视图获取(视图）
     */
    @GetMapping("/menuLink/{roleId}")
    @PreAuthorize("@sca.needAuthoritySign('system:menu:list')")
    public String authMenu(@PathVariable("roleId") Long roleId, Model model) {
        model.addAttribute("roleId", roleId);
        return "system/role/menuLink";
    }

    /**
     * 获取角色菜单权限树（已选择）（视图调用）
     */
    @GetMapping("/menuMultipleTree/{roleId}")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:role:view')")
    public List<TreeDTO> menuSelectedTree(@PathVariable("roleId") Long roleId) {
        //获取该用户所有的可支配的菜单信息
        List<SysMenuDO> menuDOList = menuService.listByConditionAndUserId(UserDetailsUtil.getUserId(), null, null, null);
        //获取该角色所有的关联菜单（作为选中的依据）
        Long[] checkIds = roleService.listMenuIdsByRoleId(roleId);
        return BuildUtils.buildMenuSelectedTree(menuDOList, checkIds);
    }

    /**
     * 保存角色菜单关联
     */

    @PostMapping("/updateMenuLink")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:role:edit')")
    @OperateLog(module = ModuleConstants.ROLE, businessType = BusinessTypeConstants.GRANT)
    public SkyResponse authMenuSave(Long roleId, String menuIds) {
        Long[] menuIds2 = Convert.toLongArray(menuIds);
        roleService.updateRoleMenuLink(roleId, menuIds2);
        return SkyResponse.success("角色菜单关联信息插入成功！");
    }


    /**
     * 导出数据
     */
    @PostMapping("/export")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:role:exprot')")
    @OperateLog(module = ModuleConstants.ROLE, businessType = BusinessTypeConstants.EXPORT)
    public SkyResponse export(SysRoleDO role) {
        List<SysRoleDO> list = roleService.listByConditionAndUserId(UserDetailsUtil.getUserId(), role, null, null);
        ExcelUtils<SysRoleDO> excelUtils = new ExcelUtils<>(SysRoleDO.class);
        return excelUtils.exportExcel(list, "角色数据");
    }
}



























