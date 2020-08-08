package com.swing.sky.tiku.module.domain;

import java.io.Serializable;

/**
 * 专业与课程关联表:对象 ti_dept_course
 *
 * @author swing
 */
public class TiDeptCourseDO implements Serializable {
    private static final long serialVersionUID = 1L;
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
    public TiDeptCourseDO() {
    }

    /**
     * 全参构造函数
     */
    public TiDeptCourseDO(Long deptId, Long courseId) {
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
        return "TiDeptCourseDO{" +
                "deptId=" + deptId + ',' +
                "courseId=" + courseId +
                '}';
    }
}