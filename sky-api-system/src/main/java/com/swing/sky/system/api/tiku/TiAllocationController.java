package com.swing.sky.system.api.tiku;

import com.swing.sky.common.utils.ArrayUtils;
import com.swing.sky.system.api.BasicController;
import com.swing.sky.system.dto.response.BuildUtils;
import com.swing.sky.system.dto.response.table.TableDataInfo;
import com.swing.sky.system.dto.response.tree.TreeDTO;
import com.swing.sky.system.framework.web.SkyResponse;
import com.swing.sky.system.module.domain.SysDeptDO;
import com.swing.sky.system.module.service.SysDeptService;
import com.swing.sky.tiku.module.dao.TiDeptCourseLinkDAO;
import com.swing.sky.tiku.module.domain.TiCourseDO;
import com.swing.sky.tiku.module.service.TiCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 为专业分配课程模块
 *
 * @author swing
 */
@Api
@Controller
@RequestMapping("tiku/allocation")
public class TiAllocationController extends BasicController {
    @Resource
    private TiDeptCourseLinkDAO deptCourseLinkDAO;

    private SysDeptService deptService;
    private TiCourseService courseService;

    @Autowired
    public void setDeptService(SysDeptService deptService) {
        this.deptService = deptService;
    }

    @Autowired
    public void setCourseService(TiCourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 默认显示自动化的课程
     */
    private static final Long DEPT_ID = 103L;

    /**
     * 主界面（视图）
     */
    @GetMapping()
    @PreAuthorize("@sca.needAuthoritySign('tiku:allocation:view')")
    public String config() {
        return "tiku/allocation/allocation";
    }

    /**
     * 获取信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:allocation:list')")
    public TableDataInfo list(TiCourseDO course) {
        String courseName = course.getCourseName();
        String courseType = course.getCourseType();
        Boolean use = course.getUse();
        //这里的deptId代表专业
        if (course.getDeptId() == null) {
            course.setDeptId(DEPT_ID);
        }
        List<TiCourseDO> list = deptCourseLinkDAO.listTwoByOneId(course.getDeptId());
        //根据条件过滤数据
        Stream<TiCourseDO> stream = list.stream();
        if (list.size() > 0) {
            if (courseName != null && !"".equals(courseName)) {
                stream = stream.filter(a -> (a.getCourseName().contains(courseName)));
            }
            if (courseType != null && !"".equals(courseType)) {
                stream = stream.filter(a -> (a.getCourseType().equals(courseType)));
            }
            if (use != null) {
                stream = stream.filter(a -> (a.getUse().equals(use)));
            }
        }
        return buildDataTable(stream.collect(Collectors.toList()));
    }

    /**
     * 获取课程选择树，用来为专业添加课程（视图）（大学-学院-课程）三层树结构
     */
    @GetMapping({"/courseRadioTreeView/{majorId}", "/courseRadioTreeView"})
    @PreAuthorize("@sca.needAuthoritySign('tiku:course:list')")
    public String courseRadioTreeView(@PathVariable(value = "majorId", required = false) Long majorId, Model model) {
        if (majorId == null) {
            majorId = DEPT_ID;
        }
        model.addAttribute("majorId", majorId);
        return "tiku/allocation/tree";
    }

    /**
     * 获取课程选择树，用来为专业添加课程
     */
    @GetMapping("/courseRadioTree/{majorId}")
    @ResponseBody
    public List<TreeDTO> courseRadioTree(@PathVariable("majorId") Long majorId) {
        //只查询到学院级别,然后再追加课程(没有课程的学院不显示,防止误选）
        //学院列表
        List<SysDeptDO> colleges = new ArrayList<>();
        //课程列表
        List<TiCourseDO> courses = new ArrayList<>();
        SysDeptDO sysDeptDO = new SysDeptDO();
        sysDeptDO.setParentId(100L);
        List<SysDeptDO> collegeList = deptService.listByCondition(sysDeptDO, null, null);
        //课程列表
        for (SysDeptDO deptDO : collegeList) {
            //查询该学院所有开设的课程
            TiCourseDO course = new TiCourseDO();
            course.setDeptId(deptDO.getId());
            List<TiCourseDO> courseList = courseService.listByCondition(course, null, null);
            //过滤该部门已选的课程
            Long[] checkedIds = deptCourseLinkDAO.listTwoIdsByOneId(majorId);
            courseList = courseList.stream().filter(a -> (!ArrayUtils.contains(checkedIds, a.getId()))).collect(Collectors.toList());
            if (courseList.size() > 0) {
                colleges.add(deptDO);
                courses.addAll(courseList);
            }
        }
        colleges.add(deptService.getById(100L));
        return BuildUtils.buildCourseSelectTree(colleges, courses);
    }

    /**
     * 添加专业课程关联
     */
    @PostMapping("/updateDeptCourseLink")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:course:updateDeptCourseLink')")
    public SkyResponse updateDeptCourseLink(Long deptId, Long courseId) {
        //如未指定，则默认为自动化专业
        if (deptId == null) {
            deptId = DEPT_ID;
        }
        deptCourseLinkDAO.insert(deptId, courseId);
        return SkyResponse.success("课程关联成功!");
    }

    /**
     * 删除课程专业关联
     */
    @PostMapping("/removeDeptCourseLink")
    @ResponseBody
    @PreAuthorize("@sca.needAuthoritySign('tiku:course:removeDeptCourseLink')")
    public SkyResponse removeDeptCourseLink(Long deptId, Long courseId) {
        //如未指定，则默认为自动化专业
        if (deptId == null) {
            deptId = DEPT_ID;
        }
        deptCourseLinkDAO.delete(deptId, courseId);
        return SkyResponse.success("课程移除成功!");
    }
}
