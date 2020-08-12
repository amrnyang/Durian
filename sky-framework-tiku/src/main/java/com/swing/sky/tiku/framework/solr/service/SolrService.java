package com.swing.sky.tiku.framework.solr.service;

import com.swing.sky.tiku.framework.solr.entity.Question;

import java.util.List;

/**
 * @author swing
 */
public interface SolrService {

    /**
     * 更新solr中的Question数据
     */
    void updateQuestionSolrData();

    /**
     * 查询答案
     *
     * @param condition 查询条件
     * @param df        默认查询域
     * @param pageStart 分页开始
     * @param pageRows  分页条数
     * @return 结果
     */
    List<Question> searchQuestion(String condition, String df, Integer pageStart, Integer pageRows);
}
