package com.swing.sky.module.system.dao;

import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.module.system.domain.SysRoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author swing
 * 筛选字段：
 * roleName:角色名称
 * use:是否使用
 * beginTime-endTime:记录创建时间
 */
public interface SysRoleDAO extends BasicDAO<SysRoleDO> {
    /**
     * 才用户拥有的数据范围内查询信息列表
     *
     * @param userId    用户id
     * @param t         条件
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 结果
     */
    List<SysRoleDO> listByConditionAndUserId(@Param("userId") Long userId, @Param("condition") SysRoleDO t, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
    //--------------------------------唯一字段校验方法-----------------------------------------

    /**
     * 校验roleName是否唯一
     *
     * @param roleName 角色名称
     * @param id       角色Id
     * @return 查询数量
     */
    int countRoleByRoleName(@Param("roleName") String roleName, @Param("id") Long id);
}