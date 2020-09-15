package com.swing.sky.es.module.service;

import com.swing.sky.es.module.domain.TiQuestionSearchDO;

import java.util.List;

/**
 * @author swing
 */
public interface TiQuestionSearchService {
    /**
     * 获取题库数据库中的所有内容
     *
     * @return 题目集合
     */
    List<TiQuestionSearchDO> listAll();
}
