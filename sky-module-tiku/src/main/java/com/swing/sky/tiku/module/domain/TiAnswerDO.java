package com.swing.sky.tiku.module.domain;

import com.swing.sky.common.basic.BasicDO;

import java.io.Serializable;

/**
 * 用户信息表:对象 ti_answer
 *
 * @author swing
 */
public class TiAnswerDO extends BasicDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 题目id
     */
    private Long questionId;
    /**
     * 答案
     */
    private String answer;
    /**
     * 解析
     */
    private String analysis;
    /**
     * 答案审核状态
     */
    private String auditStatus;
    /**
     * 点赞数（靠前显示的依据）
     */
    private Integer likeCount;

    /**
     * 无参构造函数
     */
    public TiAnswerDO() {
    }

    /**
     * 全参构造函数
     */
    public TiAnswerDO(Long questionId, String answer, String analysis, String auditStatus, Integer likeCount) {
        this.questionId = questionId;
        this.answer = answer;
        this.analysis = analysis;
        this.auditStatus = auditStatus;
        this.likeCount = likeCount;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
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

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }


    @Override
    public String toString() {
        return "TiAnswerDO{" +
                "questionId=" + questionId + ',' +
                "answer=" + answer + ',' +
                "analysis=" + analysis + ',' +
                "auditStatus=" + auditStatus + ',' +
                "likeCount=" + likeCount +
                '}' + super.toString();
    }
}