package com.swing.sky.tiku.module.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户信息表:对象 ti_question_submit
 *
 * @author swing
 */
public class TiQuestionSubmitDO implements Serializable{
    private static final long serialVersionUID=1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 提交者id
     */
    private Long creatorId;
    /**
     * 题目id
     */
    private Long questionId;
    /**
     * 内容精简
     */
    private String content;
    /**
     * 完整的内容，包含格式标签
     */
    private String fullContent;
    /**
     * 提交状态（A  审核中,B 通过，C 未通过）
     */
    private String submitStatus;
    /**
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date submitTime;
    /**
     * 提交备注
     */
    private String submitRemark;

    /**
     * 无参构造函数
     */
    public TiQuestionSubmitDO() {
    }

    /**
     * 全参构造函数
     */
    public TiQuestionSubmitDO(Long id, Long creatorId, Long questionId, String content, String fullContent, String submitStatus, Date submitTime, String submitRemark) {
        this.id = id;
        this.creatorId = creatorId;
        this.questionId = questionId;
        this.content = content;
        this.fullContent = fullContent;
        this.submitStatus = submitStatus;
        this.submitTime = submitTime;
        this.submitRemark = submitRemark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
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

    public String getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getSubmitRemark() {
        return submitRemark;
    }

    public void setSubmitRemark(String submitRemark) {
        this.submitRemark = submitRemark;
    }


    @Override
    public String toString() {
        return "TiQuestionSubmitDO{" +
                "id=" + id + ',' +
                "creatorId=" + creatorId + ',' +
                "questionId=" + questionId + ',' +
                "content=" + content + ',' +
                "fullContent=" + fullContent + ',' +
                "submitStatus=" + submitStatus + ',' +
                "submitTime=" + submitTime + ',' +
                "submitRemark=" + submitRemark  +
                '}';
    }
}