package com.swing.sky.tiku.module.domain;

import com.swing.sky.common.basic.BasicDO;

import java.io.Serializable;

/**
 * 用户信息表:对象 ti_course
 *
 * @author swing
 */
public class TiCourseDO extends BasicDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 课程号
     */
    private String courseCode;
    /**
     * 开课单位（学院）
     */
    private Long deptId;
    /**
     * 课程名
     */
    private String courseName;
    /**
     * 课程类型（A 通识必修 B 通识选修 C 专业必修 D 专业选修）
     */
    private String courseType;
    /**
     * 课程学分
     */
    private Integer credit;
    /**
     * 任课老师
     */
    private String teacher;

    /**
     * 无参构造函数
     */
    public TiCourseDO() {
    }

    /**
     * 全参构造函数
     */
    public TiCourseDO(String courseCode, Long deptId, String courseName, String courseType, Integer credit, String teacher) {
        this.courseCode = courseCode;
        this.deptId = deptId;
        this.courseName = courseName;
        this.courseType = courseType;
        this.credit = credit;
        this.teacher = teacher;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }


    @Override
    public String toString() {
        return "TiCourseDO{" +
                "courseCode=" + courseCode + ',' +
                "deptId=" + deptId + ',' +
                "courseName=" + courseName + ',' +
                "courseType=" + courseType + ',' +
                "credit=" + credit + ',' +
                "teacher=" + teacher +
                '}' + super.toString();
    }
}