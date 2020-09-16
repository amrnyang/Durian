package com.swing.sky.center.module.domain;

import com.swing.sky.common.basic.BasicDO;

import java.io.Serializable;

/**
 * 榴莲题库-课程表:对象 center_course
 *
 * @author swing
 */
public class CenterCourseDO extends BasicDO implements Serializable{
    private static final long serialVersionUID=1L;
    /**
     * 课程号
     */
    private String courseCode;
    /**
     * 开课单位（学院）
     */
    private Long collegeId;
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
    public CenterCourseDO() {
    }

    /**
     * 全参构造函数
     */
    public CenterCourseDO(String courseCode, Long collegeId, String courseName, String courseType, Integer credit, String teacher) {
        this.courseCode = courseCode;
        this.collegeId = collegeId;
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

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
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
        return "CenterCourseDO{" +
                "courseCode=" + courseCode + ',' +
                "deptId=" + collegeId + ',' +
                "courseName=" + courseName + ',' +
                "courseType=" + courseType + ',' +
                "credit=" + credit + ',' +
                "teacher=" + teacher  +
                '}'+super.toString();
    }
}