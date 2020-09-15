package com.swing.sky.tiku.module.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户信息表:对象 ti_answer_submit
 *
 * @author swing
 */
public class TiAnswerSubmitDO implements Serializable{
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
     * 答案id
     */
    private Long answerId;
    /**
     * 答案内容
     */
    private String answer;
    /**
     * 分析内容
     */
    private String analysis;
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
    public TiAnswerSubmitDO() {
    }

    /**
     * 全参构造函数
     */
    public TiAnswerSubmitDO(Long id, Long creatorId, Long answerId, String answer, String analysis, String submitStatus, Date submitTime, String submitRemark) {
        this.id = id;
        this.creatorId = creatorId;
        this.answerId = answerId;
        this.answer = answer;
        this.analysis = analysis;
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

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
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
        return "TiAnswerSubmitDO{" +
                "id=" + id + ',' +
                "creatorId=" + creatorId + ',' +
                "answerId=" + answerId + ',' +
                "answer=" + answer + ',' +
                "analysis=" + analysis + ',' +
                "submitStatus=" + submitStatus + ',' +
                "submitTime=" + submitTime + ',' +
                "submitRemark=" + submitRemark  +
                '}';
    }
}