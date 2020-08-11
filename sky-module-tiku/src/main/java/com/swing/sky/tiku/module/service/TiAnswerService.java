package com.swing.sky.tiku.module.service;

import com.swing.sky.common.basic.BasicService;
import com.swing.sky.tiku.module.domain.TiAnswerDO;
import com.swing.sky.tiku.module.domain.TiQuestionDO;

import java.util.List;

/**
 * 用户信息表
 *
 * @author swing
 */
public interface TiAnswerService extends BasicService<TiAnswerDO> {
    /**
     * 依据题目数组获取所有的答案列表
     *
     * @param list 题目集合
     * @return 答案列表
     */
    List<TiAnswerDO> listAnswersByQuestions(List<TiQuestionDO> list);
}