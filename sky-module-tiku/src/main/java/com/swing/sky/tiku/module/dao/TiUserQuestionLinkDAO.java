package com.swing.sky.tiku.module.dao;


import com.swing.sky.common.basic.BasicLinkDAO;
import com.swing.sky.oss.module.domain.DurianUserDO;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import com.swing.sky.tiku.module.domain.TiUserQuestionDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author swing
 */
public interface TiUserQuestionLinkDAO extends BasicLinkDAO<DurianUserDO, TiQuestionDO, TiUserQuestionDO> {
    /**
     * 插入一条数据
     * @param userId 用户id
     * @param questionId 题目id
     * @return 结果
     */
    int insert(@Param("userId") Long userId,@Param("questionId") Long questionId);

}