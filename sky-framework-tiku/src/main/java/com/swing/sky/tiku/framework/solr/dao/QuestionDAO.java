package com.swing.sky.tiku.framework.solr.dao;

import com.swing.sky.tiku.framework.solr.entity.Question;

import java.util.List;

/**
 * 从数据库中获取题目指定列表信息
 *
 * @author swing
 */
public interface QuestionDAO {
    /**
     * 获取所有审核通过的题目
     *
     * @return 题目列表
     */
    List<Question> listAllQuestions();
}
