package com.swing.sky.tiku.module.dao;


import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.tiku.module.domain.TiAnswerDO;

import java.util.List;

/**
 * 用户信息表
 *
 * @author swing
 */
public interface TiAnswerDAO extends BasicDAO<TiAnswerDO> {
    /**
     * 依据题目数组获取所有的答案列表
     *
     * @param ids 题目id数组
     * @return 答案列表
     */
    List<TiAnswerDO> listAnswersByQuestionIds(Long[] ids);
}