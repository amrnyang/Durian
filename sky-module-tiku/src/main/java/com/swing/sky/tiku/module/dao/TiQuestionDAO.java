package com.swing.sky.tiku.module.dao;


import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.tiku.module.domain.TiQuestionDO;

import java.util.List;

/**
 * 用户信息表
 *
 * @author swing
 */
public interface TiQuestionDAO extends BasicDAO<TiQuestionDO> {
    /**
     * 获取所有课程中的题目
     *
     * @param courseIds 课程id集合
     * @return 问题集合
     */
    List<TiQuestionDO> listQuestionByCourseIds(Long[] courseIds);
}