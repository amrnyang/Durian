package com.swing.sky.module.system.dao;

import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.module.system.domain.SysPostDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author swing
 * 筛选字段：
 * postName:部门名称
 * use:是否使用
 * beginTime-endTime:记录创建时间
 */
public interface SysPostDAO extends BasicDAO<SysPostDO> {
    /**
     * 才用户拥有的数据范围内查询信息列表
     *
     * @param userId    用户id
     * @param t         条件
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 结果
     */
    List<SysPostDO> listByConditionAndUserId(@Param("userId") Long userId, @Param("condition") SysPostDO t, @Param("beginTime") String beginTime, @Param("endTime") String endTime);


    //--------------------------------唯一字段校验方法-----------------------------------------

    /**
     * 校验岗位名称是否唯一
     *
     * @param postName 岗位名称
     * @param id   岗位id
     * @return 数量
     */
    int countByPostName(@Param("postName") String postName, @Param("id") Long id);
}