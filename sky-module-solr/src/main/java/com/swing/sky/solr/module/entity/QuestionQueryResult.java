package com.swing.sky.solr.module.entity;

import com.swing.sky.solr.module.domain.QuestionDO;

import java.util.List;

/**
 * solr查询结果封装
 *
 * @author swing
 */
public class QuestionQueryResult {
    /**
     * 题目列表
     */
    List<QuestionDO> questions;

    /**
     * 搜索到的数量
     */
    Long numFound;

    /**
     * 花费的时间（单位毫秒）
     */
    Integer queryTime;

    public List<QuestionDO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDO> questions) {
        this.questions = questions;
    }

    public Long getNumFound() {
        return numFound;
    }

    public void setNumFound(Long numFound) {
        this.numFound = numFound;
    }

    public Integer getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Integer queryTime) {
        this.queryTime = queryTime;
    }
}
