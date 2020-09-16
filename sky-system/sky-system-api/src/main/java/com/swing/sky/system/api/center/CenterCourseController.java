package com.swing.sky.system.api.center;

import cn.hutool.core.convert.Convert;
import com.swing.sky.center.module.domain.CenterCourseDO;
import com.swing.sky.center.module.domain.CenterDeptDO;
import com.swing.sky.center.module.service.CenterCourseService;
import com.swing.sky.center.module.service.CenterDeptService;
import com.swing.sky.system.module.annotation.OperateLog;
import com.swing.sky.system.module.constants.BusinessTypeConstants;
import com.swing.sky.system.module.constants.ModuleConstants;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.BuildUtils;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.system.dto.response.tree.TreeDTO;
import com.swing.sky.common.web.SkyResponse;
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
@RequestMapping("center/course")
public class CenterCourseController extends BasicController {
    private CenterCourseService courseService;
    private CenterDeptService deptService;

    /**
     * 校园ID
     */
    private static final Long COLLEGE_ID = 100L;

    @Autowired
    public void setCourseService(CenterCourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setDeptService(CenterDeptService deptService) {
        this.deptService = deptService;
    }

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('center:course:view')")
    public String course() {
        return "center/course/course";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('center:course:list')")
    public TableDataInfo list(CenterCourseDO course) {
        startPage();
        if (course.getCollegeId() != null) {
            //如果选中校园ID，则表示显示全部课程信息
            if (course.getCollegeId().equals(COLLEGE_ID)) {
                course.setCollegeId(null);
            }
        }
        List<CenterCourseDO> list = courseService.listByCondition(course, null, null);
        return buildDataTable(list);
    }

    /**
     * 新增（视图）
     */
    @GetMapping("/add")
    @PreAuthorize("@sca.needAuthoritySign('center:course:add')")
    public String add() {
        return "center/course/add";
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('center:course:add')")
    @OperateLog(module = ModuleConstants.TIKU_COURSE, businessType = BusinessTypeConstants.INSERT)
    public SkyResponse addSave(@Validated CenterCourseDO course) {
        courseService.insert(course);
        return SkyResponse.success("配置信息插入成功！");
    }

    /**
     * 更新（视图）
     */
    @GetMapping("/edit/{courseId}")
    @PreAuthorize("@sca.needAuthoritySign('center:course:edit')")
    public String edit(@PathVariable("courseId") Long courseId, Model model) {
        CenterCourseDO course = courseService.getById(courseId);
        model.addAttribute("course", course);
        String deptName = deptService.getById(course.getCollegeId()).getDeptName();
        model.addAttribute("deptName", deptName);
        return "center/course/edit";
    }

    /**
     * 更新
     */
    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('center:course:edit')")
    @OperateLog(module = ModuleConstants.TIKU_COURSE, businessType = BusinessTypeConstants.UPDATE)
    public SkyResponse editSave(@Validated CenterCourseDO course) {
        courseService.update(course);
        return SkyResponse.success("课程信息更新成功！");
    }

    /**
     * 批量删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('center:course:remove')")
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
    @PreAuthorize("@sca.needAuthoritySign('center:course:list')")
    public String deptRadioTreeView(@PathVariable(value = "deptId", required = false) Long deptId, Model model) {
        if (deptId != null) {
            model.addAttribute("dept", deptService.getById(deptId));
        }
        model.addAttribute("dept", deptService.getById(101L));
        return "center/course/tree";
    }

    /**
     * 获取部门选择树，用来选择课程的开课学院
     */
    @GetMapping("/collegeRadioTree")
    @ResponseBody
    public List<TreeDTO> deptRadioTree() {
        //只查询到学院级别
        CenterDeptDO deptDO = new CenterDeptDO();
        deptDO.setParentId(100L);
        List<CenterDeptDO> deptDOList = deptService.listByCondition(deptDO, null, null);
        deptDOList.add(deptService.getById(100L));
        return BuildUtils.buildCenterDeptSelectTree(deptDOList);
    }
}
