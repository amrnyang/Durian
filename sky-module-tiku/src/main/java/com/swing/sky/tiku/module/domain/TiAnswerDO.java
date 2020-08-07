package com.swing.sky.tiku.module.domain;

import com.swing.sky.common.basic.BasicDO;

import java.io.Serializable;
/**
 * 用户信息表:对象 ti_answer
 *
 * @author swing
 */
public class TiAnswerDO extends BasicDO implements Serializable{
    private static final long serialVersionUID=1L;
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
     * 无参构造函数
     */
    public TiAnswerDO() {
    }

    /**
     * 全参构造函数
     */
    public TiAnswerDO(Long questionId, String answer, String analysis) {
        this.questionId = questionId;
        this.answer = answer;
        this.analysis = analysis;
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


    @Override
    public String toString() {
        return "TiAnswerDO{" +
                "questionId=" + questionId + ',' +
                "answer=" + answer + ',' +
                "analysis=" + analysis  +
                '}'+super.toString();
    }
}