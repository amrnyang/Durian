package com.swing.sky.module.system.dao;

import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.module.system.domain.SysDeptDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author swing
 * 筛选字段：
 * deptName:部门名称
 * phone:电话
 * leader:部门负责人
 * use:是否使用
 * beginTime-endTime:记录创建时间
 */
public interface SysDeptDAO extends BasicDAO<SysDeptDO> {
    /**
     * 才用户拥有的数据范围内查询信息列表
     *
     * @param userId    用户id
     * @param t         条件
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 结果
     */
    List<SysDeptDO> listByConditionAndUserId(@Param("userId") Long userId, @Param("condition") SysDeptDO t, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    //--------------------------------唯一字段校验方法-----------------------------------------

    /**
     * 校验同一父部门下是否有该部门名称
     *
     * @param parentId 父id
     * @param deptName 部门名称
     * @param id       部门Id
     * @return 数量
     */
    int countByParentIdAndDeptName(@Param("parentId") Long parentId, @Param("deptName") String deptName, @Param("id") Long id);
}