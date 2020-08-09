package com.swing.sky.tiku.module.service;


import com.swing.sky.common.basic.BasicService;
import com.swing.sky.tiku.module.domain.TiQuestionDO;

import java.util.List;

/**
 * 用户信息表
 *
 * @author swing
 */
public interface TiQuestionService extends BasicService<TiQuestionDO> {
    /**
     * 获取所有课程中未审核的题目
     *
     * @param courseIds   课程id集合
     * @return 问题集合
     */
    List<TiQuestionDO> listQuestionByCourseIdsAndAuditStatue(Long[] courseIds);
}