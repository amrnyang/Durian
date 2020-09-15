package com.swing.sky.tiku.module.domain;

import java.io.Serializable;
/**
 * 用户与岗位关联表:对象 ti_user_question
 *
 * @author swing
 */
public class TiUserQuestionDO implements Serializable{
    private static final long serialVersionUID=1L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户收藏的题目id
     */
    private Long questionId;

    /**
     * 无参构造函数
     */
    public TiUserQuestionDO() {
    }

    /**
     * 全参构造函数
     */
    public TiUserQuestionDO(Long userId, Long questionId) {
        this.userId = userId;
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }


    @Override
    public String toString() {
        return "TiUserQuestionDO{" +
                "userId=" + userId + ',' +
                "questionId=" + questionId  +
                '}';
    }
}