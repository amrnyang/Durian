package com.swing.sky.tiku.module.domain;

import com.swing.sky.common.basic.BasicDO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户信息表:对象 ti_question
 *
 * @author swing
 */
public class TiQuestionDO extends BasicDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 发布者id
     */
    private Long creatorId;
    /**
     * 题型（A 选择题，B 填空题 ,C 计算题）
     */
    private String questionType;
    /**
     * 题目年份
     */
    private String year;
    /**
     * 学期（1上学期 0 下学期）
     */
    private Boolean term;
    /**
     * 课程id
     */
    @NotNull(message = "题目所属课程不为空")
    private Long courseId;
    /**
     * 章节id
     */
    private Long chapterId;
    /**
     * 知识点
     */
    private String reviewPoint;
    /**
     * 简要内容（无标签），便于搜索引擎快速搜索
     */
    private String content;
    /**
     * 完整的内容，包含格式标签
     */
    private String fullContent;
    /**
     * 题目分值
     */
    private Integer score;
    /**
     * 题目审核状态（A 审核中,B 通过，C 未通过）
     */
    private String auditStatus;

    /**
     * 无参构造函数
     */
    public TiQuestionDO() {
    }

    /**
     * 全参构造函数
     */
    public TiQuestionDO(String questionType, Long creatorId, String year, Boolean term, Long courseId, Long chapterId, String reviewPoint, String content, String fullContent, Integer score, String auditStatus) {
        this.creatorId = creatorId;
        this.questionType = questionType;
        this.year = year;
        this.term = term;
        this.courseId = courseId;
        this.chapterId = chapterId;
        this.reviewPoint = reviewPoint;
        this.content = content;
        this.fullContent = fullContent;
        this.score = score;
        this.auditStatus = auditStatus;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Boolean getTerm() {
        return term;
    }

    public void setTerm(Boolean term) {
        this.term = term;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
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

    public String getFullContent() {
        return fullContent;
    }

    public void setFullContent(String fullContent) {
        this.fullContent = fullContent;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    @Override
    public String toString() {
        return "TiQuestionDO{" +
                "creatorId=" + creatorId +
                ", questionType='" + questionType + '\'' +
                ", year='" + year + '\'' +
                ", term=" + term +
                ", courseId=" + courseId +
                ", chapterId=" + chapterId +
                ", reviewPoint='" + reviewPoint + '\'' +
                ", content='" + content + '\'' +
                ", fullContent='" + fullContent + '\'' +
                ", score=" + score +
                ", auditStatus='" + auditStatus + '\'' +
                ", id=" + id +
                ", use=" + use +
                ", orderNum=" + orderNum +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}