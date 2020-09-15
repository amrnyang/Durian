package com.swing.sky.es.module.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * 需要录入进搜索引擎中的题目信息
 *
 * @author swing
 */
public class TiQuestionSearchDO {
    /**
     * 题目id
     */
    private Long id;
    /**
     * 发布者id
     */
    private Long creatorId;

    /**
     * 发布者姓名
     */
    private String creatorName;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 题型（A 选择题，B 填空题 ,C 计算题）
     */
    private String questionType;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 知识点
     */
    private String reviewPoint;

    /**
     * 简要内容（无标签），便于搜索引擎快速搜索
     */
    private String content;

    /**
     * 题目当前答案数
     */
    private Integer answerNum;

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getReviewPoint() {
        return reviewPoint;
    }

    public void setReviewPoint(String reviewPoint) {
        this.reviewPoint = reviewPoint;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    @Override
    public String toString() {
        return "TiQuestionSearchDO{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", creatorName='" + creatorName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", questionType='" + questionType + '\'' +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", reviewPoint='" + reviewPoint + '\'' +
                ", content='" + content + '\'' +
                ", answerNum=" + answerNum +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
