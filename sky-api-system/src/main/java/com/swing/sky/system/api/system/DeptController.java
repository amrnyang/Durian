package com.swing.sky.system.api.system;

import cn.hutool.core.convert.Convert;
import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.BuildUtils;
import com.swing.sky.system.dto.response.tree.TreeDTO;
import com.swing.sky.system.framework.security.utils.UserDetailsUtil;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.system.module.domain.SysDeptDO;
import com.swing.sky.system.module.domain.SysPostDO;
import com.swing.sky.system.module.service.SysDeptService;
import com.swing.sky.system.module.service.SysPostService;
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
 * 关于接口命名的说明，根据数据展示的结构，做如下规范：
 * 树形结构：
 * 1.作为主页面展示的树形结构数据，直接返回List，然后在前端指定父id，再由前端自行渲染，该类接口命名为: /list
 * 2.作为表单选择项时（单选），返回List<TreeDTO>类型的数据，接口命名： 模块名+RadioTree
 * 2.作为表单选择项时（单选）（返回视图），模块名+RadioTreeView
 * 3.作为表单选择项时（多选），返回List<TreeDTO>类型的数据（此时需完善TreeDTO中的checked字段，表示是否勾选），接口命名： 模块名+MultipleTree
 * 3.作为表单选择项时（多选）(返回视图），接口命名： 模块名+MultipleTreeView
 */
@Api
@Controller
@RequestMapping("/system/dept")
public class DeptController extends BasicController {
    private SysDeptService deptService;

    private SysPostService postService;

    @Autowired
    public void setPostService(SysPostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setDeptService(SysDeptService deptService) {
        this.deptService = deptService;
    }

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('system:dept:view')")
    public String dept() {
        return "system/dept/dept";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dept:list')")
    public List<SysDeptDO> list(SysDeptDO dept) {
        return deptService.listByConditionAndUserId(UserDetailsUtil.getUserId(), dept, null, null);
    }

    /**
     * 新增（视图）
     */
    @GetMapping("/add/{parentId}")
    @PreAuthorize("@sca.needAuthoritySign('system:dept:add')")
    public String add(@PathVariable("parentId") Long parentId, Model model) {
        SysDeptDO parentDept = null;
        if (0L != parentId) {
            parentDept = deptService.getById(parentId);
        } else {
            parentDept = new SysDeptDO();
            parentDept.setId(0L);
            parentDept.setDeptName("无");
        }
        model.addAttribute("dept", parentDept);
        return "system/dept/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dept:add')")
    @OperateLog(module = ModuleConstants.DEPT, businessType = BusinessTypeConstants.INSERT)
    public SkyResponse addSave(@Validated SysDeptDO dept) {
        deptService.insert(dept);
        return SkyResponse.success("新增部门信息成功！");
    }


    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{deptId}")
    @PreAuthorize("@sca.needAuthoritySign('system:dept:edit')")
    public String edit(@PathVariable("deptId") Long deptId, Model model) {
        Long parentId = deptService.getById(deptId).getParentId();
        String parentName = parentId == 0L ? null : deptService.getById(parentId).getDeptName();
        model.addAttribute("dept", deptService.getById(deptId));
        model.addAttribute("parentName", parentName);
        return "system/dept/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dept:edit')")
    @OperateLog(module = ModuleConstants.DEPT, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse editSave(@Validated SysDeptDO dept) {
        deptService.update(dept);
        return SkyResponse.success("更新部门信息成功！");
    }

    /**
     * 删除
     */
    @GetMapping("/remove/{deptId}")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dept:remove')")
    @OperateLog(module = ModuleConstants.DEPT, businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(@PathVariable("deptId") Long deptId) {
        deptService.deleteById(deptId);
        return SkyResponse.success("部门删除成功！");
    }


    /**
     * 获取部门选择树，用来选择部门的父部门（视图）
     */
    @GetMapping({"/deptRadioTreeView/{deptId}", "/deptRadioTreeView"})
    @PreAuthorize("@sca.needAuthoritySign('system:dept:list')")
    public String deptRadioTreeView(@PathVariable(value = "deptId", required = false) Long deptId, Model model) {
        if (deptId != null) {
            model.addAttribute("dept", deptService.getById(deptId));
        }
        model.addAttribute("dept", deptService.getById(100L));
        return "system/dept/tree";
    }

    /**
     * 获取部门选择树，用来选择部门的父部门
     */
    @GetMapping("/deptRadioTree")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dept:list')")
    public List<TreeDTO> deptRadioTree() {
        List<SysDeptDO> deptDOList = deptService.listByConditionAndUserId(UserDetailsUtil.getUserId(), null, null, null);
        return BuildUtils.buildDeptSelectTree(deptDOList);
    }

    /**
     * 获取部门岗位关联（视图）
     */
    @GetMapping("/postLink/{deptId}")
    @PreAuthorize("@sca.needAuthoritySign('system:post:list')")
    public String postLink(@PathVariable("deptId") Long deptId, Model model) {
        //获取该用户所有的可支配岗位
        List<SysPostDO> postDOList = postService.listByConditionAndUserId(UserDetailsUtil.getUserId(), null, null, null);
        //获取该部门的所有关联岗位
        Long[] checkIds = deptService.listPostIdsByDeptId(deptId);
        model.addAttribute("deptId", deptId);
        model.addAttribute("posts", BuildUtils.buildPostSelectedList(postDOList, checkIds));
        return "system/dept/postLink";
    }

    /**
     * 更新部门岗位关联
     */
    @PostMapping("/updatePostLink")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('system:dept:edit')")
    @OperateLog(module = ModuleConstants.DEPT, businessType = BusinessTypeConstants.GRANT)
    public SkyResponse updatePostLink(Long deptId, String postIds) {
        Long[] postIds2 = Convert.toLongArray(postIds);
        deptService.updateDeptPostLink(deptId, postIds2);
        return SkyResponse.success("部门岗位关联信息插入成功！");
    }
}













































