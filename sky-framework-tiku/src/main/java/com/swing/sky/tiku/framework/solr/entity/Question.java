package com.swing.sky.tiku.framework.solr.entity;

import java.io.Serializable;

/**
 * 搜索引擎中的问题对象
 *
 * @author swing
 */
public class Question implements Serializable {
    /**
     * 题目id
     */
    private Long tiQuestionId;
    /**
     * 课程id
     */
    private Long tiQuestionCourseId;
    /**
     * 课程名
     */
    private String tiCourseCourseName;
    /**
     * 知识点
     */
    private String tiQuestionReviewPoint;
    /**
     * 题目内容
     */
    private String tiQuestionContent;
    /**
     * 题目类型
     */
    private String tiQuestionQuestionType;

    public Long getTiQuestionId() {
        return tiQuestionId;
    }

    public void setTiQuestionId(Long tiQuestionId) {
        this.tiQuestionId = tiQuestionId;
    }

    public Long getTiQuestionCourseId() {
        return tiQuestionCourseId;
    }

    public void setTiQuestionCourseId(Long tiQuestionCourseId) {
        this.tiQuestionCourseId = tiQuestionCourseId;
    }

    public String getTiCourseCourseName() {
        return tiCourseCourseName;
    }

    public void setTiCourseCourseName(String tiCourseCourseName) {
        this.tiCourseCourseName = tiCourseCourseName;
    }

    public String getTiQuestionReviewPoint() {
        return tiQuestionReviewPoint;
    }

    public void setTiQuestionReviewPoint(String tiQuestionReviewPoint) {
        this.tiQuestionReviewPoint = tiQuestionReviewPoint;
    }

    public String getTiQuestionContent() {
        return tiQuestionContent;
    }

    public void setTiQuestionContent(String tiQuestionContent) {
        this.tiQuestionContent = tiQuestionContent;
    }

    public String getTiQuestionQuestionType() {
        return tiQuestionQuestionType;
    }

    public void setTiQuestionQuestionType(String tiQuestionQuestionType) {
        this.tiQuestionQuestionType = tiQuestionQuestionType;
    }

    @Override
    public String toString() {
        return "Question{" +
                "tiQuestionId=" + tiQuestionId +
                ", tiQuestionCourseId=" + tiQuestionCourseId +
                ", tiCourseCourseName='" + tiCourseCourseName + '\'' +
                ", tiQuestionReviewPoint='" + tiQuestionReviewPoint + '\'' +
                ", tiQuestionContent='" + tiQuestionContent + '\'' +
                ", tiQuestionQuestionType='" + tiQuestionQuestionType + '\'' +
                '}';
    }
}
