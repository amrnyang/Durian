package com.swing.sky.system.api.system;

import cn.hutool.core.convert.Convert;
import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.BuildUtils;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.system.framework.excel.util.ExcelUtils;
import com.swing.sky.system.framework.security.utils.UserDetailsUtil;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.system.module.dao.SysUserPostLinkDAO;
import com.swing.sky.system.module.dao.SysUserRoleLinkDAO;
import com.swing.sky.system.module.domain.SysPostDO;
import com.swing.sky.system.module.domain.SysRoleDO;
import com.swing.sky.system.module.domain.SysUserDO;
import com.swing.sky.system.module.service.SysDeptService;
import com.swing.sky.system.module.service.SysPostService;
import com.swing.sky.system.module.service.SysRoleService;
import com.swing.sky.system.module.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Api
@Controller
@RequestMapping("system/user")
public class UserController extends BasicController {
    private SysUserService userService;

    private SysPostService postService;

    private SysRoleService roleService;

    private SysDeptService deptService;

    @Resource
    private SysUserPostLinkDAO userPostLinkDAO;

    @Resource
    private SysUserRoleLinkDAO userRoleLinkDAO;

    @Autowired
    public void setUserService(SysUserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDeptService(SysDeptService deptService) {
        this.deptService = deptService;
    }

    @Autowired
    public void setPostService(SysPostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setRoleService(SysRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('system:user:view')")
    public String user() {
        return "system/user/user";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:user:list')")
    public TableDataInfo list(SysUserDO user) {
        startPage();
        List<SysUserDO> list = userService.listByConditionAndUserId(UserDetailsUtil.getUserId(), user, null, null);
        return buildDataTable(list);
    }

    /**
     * 新增（视图）
     */
    @GetMapping("/add")
    @PreAuthorize("@sca.needAuthoritySign('system:user:add')")
    public String add() {
        return "system/user/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:user:add')")
    @OperateLog(module = ModuleConstants.USER, businessType = BusinessTypeConstants.INSERT)
    public SkyResponse addSave(@Validated SysUserDO user) {
        userService.insert(user);
        return SkyResponse.success("用户信息插入成功！");
    }

    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{userId}")
    @PreAuthorize("@sca.needAuthoritySign('system:user:edit')")
    public String edit(@PathVariable("userId") Long userId, Model model) {
        SysUserDO user = userService.getById(userId);
        model.addAttribute("user", user);
        String deptName = deptService.getById(user.getDeptId()).getDeptName();
        model.addAttribute("deptName", deptName);
        return "system/user/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:user:edit')")
    @OperateLog(module = ModuleConstants.USER, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse editSave(@Validated SysUserDO user) {
        userService.update(user);
        return SkyResponse.success("用户信息更新成功！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:user:remove')")
    @OperateLog(module = ModuleConstants.USER, businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(String ids) {
        Long[] userIds = Convert.toLongArray(ids);
        userService.batchDeleteByIds(userIds);
        return SkyResponse.success("用户信息删除成功！");
    }

    /**
     * 获取用户岗位关联（视图）
     */
    @GetMapping("/postLink/{userId}")
    @PreAuthorize("@sca.needAuthoritySign('system:post:list')")
    public String postLink(@PathVariable("userId") Long userId, Model model) {
        //获取该用户所有的可支配岗位
        List<SysPostDO> postDOList = postService.listByConditionAndUserId(UserDetailsUtil.getUserId(), null, null, null);
        //获取该用户的所有关联岗位
        Long[] checkIds = userPostLinkDAO.listTwoIdsByOneId(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("posts", BuildUtils.buildPostSelectedList(postDOList, checkIds));
        return "system/user/postLink";
    }

    /**
     * 更新用户岗位关联
     */
    @PostMapping("/updatePostLink")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:user:view')")
    public SkyResponse updatePostLink(Long userId, String postIds) {
        Long[] postIds2 = Convert.toLongArray(postIds);
        userService.updateUserPostLink(userId, postIds2);
        return SkyResponse.success("用户岗位关联信息插入成功！");
    }

    /**
     * 获取用户角色关联（视图）
     */
    @GetMapping("/roleLink/{userId}")
    @PreAuthorize("@sca.needAuthoritySign('system:role:list')")
    public String roleLink(@PathVariable("userId") Long userId, Model model) {
        //获取该用户所有的可支配岗位
        List<SysRoleDO> roleDOList = roleService.listByConditionAndUserId(UserDetailsUtil.getUserId(), null, null, null);
        //获取该用户的所有关联岗位
        Long[] checkIds = userRoleLinkDAO.listTwoIdsByOneId(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("roles", BuildUtils.buildRoleSelectedList(roleDOList, checkIds));
        return "system/user/roleLink";
    }

    /**
     * 更新用户角色关联
     */
    @PostMapping("/updateRoleLink")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:user:view')")
    public SkyResponse updateRoleLink(Long userId, String roleIds) {
        Long[] roleIds2 = Convert.toLongArray(roleIds);
        userService.updateUserRoleLink(userId, roleIds2);
        return SkyResponse.success("用户角色关联信息插入成功！");
    }

    /**
     * 重置密码（视图)
     */
    @GetMapping("/resetPassword/{userId}")
    @PreAuthorize("@sca.needAuthoritySign('system:user:resetPassword')")
    public String resetPassword(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("user", userService.getById(userId));
        return "system/user/resetPassword";
    }

    /**
     * 重置密码
     */
    @PostMapping("/resetPassword")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:user:resetPassword')")
    @OperateLog(module = ModuleConstants.USER, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse resetPasswordSave(SysUserDO user) {
        //密码加密保存
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.update(user);
        return SkyResponse.success("密码重置成功！");
    }

    /**
     * 导出数据
     */
    @PostMapping("/export")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:user:export')")
    @OperateLog(module = ModuleConstants.USER, businessType = BusinessTypeConstants.EXPORT)
    public SkyResponse export(SysUserDO user) {
        List<SysUserDO> list = userService.listByConditionAndUserId(UserDetailsUtil.getUserId(), user, null, null);
        ExcelUtils<SysUserDO> excelUtils = new ExcelUtils<>(SysUserDO.class);
        return excelUtils.exportExcel(list, "用户数据");
    }

}





























