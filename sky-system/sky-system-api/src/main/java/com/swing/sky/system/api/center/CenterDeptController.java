package com.swing.sky.system.api.center;

import com.swing.sky.center.module.domain.CenterDeptDO;
import com.swing.sky.center.module.service.CenterDeptService;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.BuildUtils;
import com.swing.sky.system.dto.response.tree.TreeDTO;
import com.swing.sky.system.module.annotation.OperateLog;
import com.swing.sky.system.module.constants.BusinessTypeConstants;
import com.swing.sky.system.module.constants.ModuleConstants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 信息中心的专业管理
 *
 * @author swing
 */
@Api
@Controller
@RequestMapping("/center/dept")
public class CenterDeptController extends BasicController {
    private CenterDeptService deptService;

    @Autowired
    public void setDeptService(CenterDeptService deptService) {
        this.deptService = deptService;
    }

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('center:dept:view')")
    public String dept() {
        return "center/dept/dept";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('center:dept:list')")
    public List<CenterDeptDO> list(CenterDeptDO dept) {
        return deptService.listByCondition(dept, null, null);
    }

    /**
     * 新增（视图）
     */
    @GetMapping("/add/{parentId}")
    @PreAuthorize("@sca.needAuthoritySign('center:dept:add')")
    public String add(@PathVariable("parentId") Long parentId, Model model) {
        CenterDeptDO parentDept = null;
        if (0L != parentId) {
            parentDept = deptService.getById(parentId);
        } else {
            parentDept = new CenterDeptDO();
            parentDept.setId(0L);
            parentDept.setDeptName("无");
        }
        model.addAttribute("dept", parentDept);
        return "center/dept/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('center:dept:add')")
    @OperateLog(module = ModuleConstants.DEPT, businessType = BusinessTypeConstants.INSERT)
    public SkyResponse addSave(@Validated CenterDeptDO dept) {
        deptService.insert(dept);
        return SkyResponse.success("新增部门信息成功！");
    }


    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{deptId}")
    @PreAuthorize("@sca.needAuthoritySign('center:dept:edit')")
    public String edit(@PathVariable("deptId") Long deptId, Model model) {
        Long parentId = deptService.getById(deptId).getParentId();
        String parentName = parentId == 0L ? null : deptService.getById(parentId).getDeptName();
        model.addAttribute("dept", deptService.getById(deptId));
        model.addAttribute("parentName", parentName);
        return "center/dept/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('center:dept:edit')")
    @OperateLog(module = ModuleConstants.DEPT, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse editSave(@Validated CenterDeptDO dept) {
        deptService.update(dept);
        return SkyResponse.success("更新部门信息成功！");
    }

    /**
     * 删除
     */
    @GetMapping("/remove/{deptId}")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('center:dept:remove')")
    @OperateLog(module = ModuleConstants.DEPT, businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(@PathVariable("deptId") Long deptId) {
        deptService.deleteById(deptId);
        return SkyResponse.success("部门删除成功！");
    }


    /**
     * 获取部门选择树，用来选择部门的父部门（视图）
     */
    @GetMapping({"/deptRadioTreeView/{deptId}", "/deptRadioTreeView"})
    @PreAuthorize("@sca.needAuthoritySign('center:dept:list')")
    public String deptRadioTreeView(@PathVariable(value = "deptId", required = false) Long deptId, Model model) {
        if (deptId != null) {
            model.addAttribute("dept", deptService.getById(deptId));
        }
        model.addAttribute("dept", deptService.getById(100L));
        return "center/dept/tree";
    }

    /**
     * 获取部门选择树，用来选择部门的父部门
     */
    @GetMapping("/deptRadioTree")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('center:dept:list')")
    public List<TreeDTO> deptRadioTree() {
        List<CenterDeptDO> deptDOList = deptService.listByCondition(null, null, null);
        return BuildUtils.buildCenterDeptSelectTree(deptDOList);
    }
}