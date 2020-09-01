package com.swing.sky.tiku.api;

import com.swing.sky.system.module.domain.SysDeptDO;
import com.swing.sky.system.module.service.SysDeptService;
import com.swing.sky.tiku.dto.BuildUtils;
import com.swing.sky.tiku.dto.TreeDTO;
import com.swing.sky.tiku.module.dao.TiDeptCourseLinkDAO;
import com.swing.sky.tiku.module.domain.TiCourseDO;
import com.swing.sky.tiku.module.service.TiCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 为前端提供德课程信息服务
 *
 * @author swing
 * @since 2020-9-1
 */
@RestController
@RequestMapping("tiku/course")
public class CourseController {
    private TiCourseService courseService;
    private SysDeptService deptService;
    @Resource
    private TiDeptCourseLinkDAO deptCourseLinkDAO;

    @Autowired
    public void setDeptService(SysDeptService deptService) {
        this.deptService = deptService;
    }

    @Autowired
    public void setCourseService(TiCourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 获取课程选择树，用来筛选题目信息
     */
    @GetMapping("/courseTree")
    @ResponseBody
    public List<TreeDTO> courseRadioTree() {
        //只查询到学院级别,然后再追加课程(没有课程的学院不显示,防止误选）
        //学院列表
        List<SysDeptDO> colleges = new ArrayList<>();
        //课程列表
        List<TiCourseDO> courses = new ArrayList<>();
        //获取学院列表
        SysDeptDO sysDeptDO = new SysDeptDO();
        sysDeptDO.setParentId(100L);
        List<SysDeptDO> collegeList = deptService.listByCondition(sysDeptDO, null, null);
        //课程列表
        for (SysDeptDO deptDO : collegeList) {
            //查询该学院所有开设的课程
            TiCourseDO course = new TiCourseDO();
            course.setDeptId(deptDO.getId());
            List<TiCourseDO> courseList = courseService.listByCondition(course, null, null);
            //没有课程的学院不显示，防止误选
            if (courseList.size() > 0) {
                colleges.add(deptDO);
                courses.addAll(courseList);
            }
        }
        return BuildUtils.buildCourseSelectTree(colleges, courses);
    }

}
