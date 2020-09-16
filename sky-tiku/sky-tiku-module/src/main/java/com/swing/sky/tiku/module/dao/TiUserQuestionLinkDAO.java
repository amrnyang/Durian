package com.swing.sky.tiku.module.dao;


import com.swing.sky.tiku.module.domain.TiQuestionDO;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author swing
 */
public interface TiUserQuestionLinkDAO {
    /**
     * 插入一条数据
     *
     * @param userId     用户id
     * @param questionId 题目id
     * @return 结果
     */
    int insert(@Param("userId") Long userId, @Param("questionId") Long questionId);


    /**
     * 列出用户收藏的题目
     *
     * @param id 用户id
     * @return 题库列表
     */
    List<TiQuestionDO> listQuestionByUserId(Long id);

    /**
     * 判断该题是否收藏
     *
     * @param userId     用户id
     * @param questionId 题目id
     * @return 结果
     */
    int isCollection(@Param("userId") Long userId, @Param("questionId") Long questionId);

}