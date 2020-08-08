package com.swing.sky.system.api.tiku;

import cn.hutool.core.convert.Convert;
import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.BuildUtils;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.system.dto.response.tree.TreeDTO;
import com.swing.sky.system.framework.web.SkyResponse;
import com.swing.sky.system.module.domain.SysDeptDO;
import com.swing.sky.system.module.service.SysDeptService;
import com.swing.sky.tiku.module.domain.TiCourseDO;
import com.swing.sky.tiku.module.service.TiCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题库模块-课程管理
 *
 * @author swing
 */
@Api
@Controller
@RequestMapping("tiku/course")
public class TiCourseController extends BasicController {
    private TiCourseService courseService;
    private SysDeptService deptService;

    @Autowired
    public void setCourseService(TiCourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setDeptService(SysDeptService deptService) {
        this.deptService = deptService;
    }

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('tiku:course:view')")
    public String config() {
        return "tiku/course/course";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:course:list')")
    public TableDataInfo list(TiCourseDO course) {
        startPage();
        List<TiCourseDO> list = courseService.listByCondition(course, null, null);
        return buildDataTable(list);
    }

    /**
     * 新增（视图）
     */
    @GetMapping("/add")
    @PreAuthorize("@sca.needAuthoritySign('tiku:course:add')")
    public String add() {
        return "tiku/course/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:course:add')")
    @OperateLog(module = ModuleConstants.TIKU_COURSE, businessType = BusinessTypeConstants.INSERT)
    public SkyResponse addSave(@Validated TiCourseDO course) {
        courseService.insert(course);
        return SkyResponse.success("配置信息插入成功！");
    }

    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{courseId}")
    @PreAuthorize("@sca.needAuthoritySign('tiku:course:edit')")
    public String edit(@PathVariable("courseId") Long courseId, Model model) {
        TiCourseDO course = courseService.getById(courseId);
        model.addAttribute("course", course);
        String deptName = deptService.getById(course.getDeptId()).getDeptName();
        model.addAttribute("deptName", deptName);
        return "tiku/course/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:course:edit')")
    @OperateLog(module = ModuleConstants.TIKU_COURSE, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse editSave(@Validated TiCourseDO course) {
        courseService.update(course);
        return SkyResponse.success("课程信息更新成功！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:course:remove')")
    @OperateLog(module = ModuleConstants.TIKU_COURSE, businessType = BusinessTypeConstants.DELETE)
    public SkyResponse remove(String ids) {
        Long[] courseIds = Convert.toLongArray(ids);
        courseService.batchDeleteByIds(courseIds);
        return SkyResponse.success("课程信息删除成功！");
    }

    /**
     * 获取学院选择树，用来选择开课单位（视图）
     */
    @GetMapping({"/collegeRadioTreeView/{deptId}", "/collegeRadioTreeView"})
    @PreAuthorize("@sca.needAuthoritySign('tiku:course:list')")
    public String deptRadioTreeView(@PathVariable(value = "deptId", required = false) Long deptId, Model model) {
        if (deptId != null) {
            model.addAttribute("dept", deptService.getById(deptId));
        }
        model.addAttribute("dept", deptService.getById(101L));
        return "tiku/course/tree";
    }

    /**
     * 获取部门选择树，用来选择部门的父部门
     */
    @GetMapping("/collegeRadioTree")
    @ResponseBody
    public List<TreeDTO> deptRadioTree() {
        //只查询到学院级别
        SysDeptDO sysDeptDO = new SysDeptDO();
        sysDeptDO.setParentId(100L);
        List<SysDeptDO> deptDOList = deptService.listByCondition(sysDeptDO, null, null);
        deptDOList.add(deptService.getById(100L));
        return BuildUtils.buildDeptSelectTree(deptDOList);
    }
}
