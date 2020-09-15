package com.swing.sky.center.module.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 榴莲题库-课程与专业关联表:对象 center_dept_course
 *
 * @author swing
 */
public class CenterDeptCourseDO implements Serializable{
    private static final long serialVersionUID=1L;
    /**
     * 专业id
     */
    private Long deptId;
    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 无参构造函数
     */
    public CenterDeptCourseDO() {
    }

    /**
     * 全参构造函数
     */
    public CenterDeptCourseDO(Long deptId, Long courseId) {
        this.deptId = deptId;
        this.courseId = courseId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }


    @Override
    public String toString() {
        return "CenterDeptCourseDO{" +
                "deptId=" + deptId + ',' +
                "courseId=" + courseId  +
                '}';
    }
}