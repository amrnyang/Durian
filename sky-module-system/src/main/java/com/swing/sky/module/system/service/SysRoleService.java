package com.swing.sky.module.system.service;

import com.swing.sky.common.basic.BasicService;
import com.swing.sky.module.system.domain.SysRoleDO;

import java.util.List;

/**
 * @author swing
 */
public interface SysRoleService extends BasicService<SysRoleDO> {
    /**
     * 才用户拥有的数据范围内查询信息列表
     *
     * @param userId    用户id
     * @param t         条件
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 结果
     */
    List<SysRoleDO> listByConditionAndUserId(Long userId, SysRoleDO t, String beginTime, String endTime);

    /**
     * 根据角色id获取菜单id
     *
     * @param menuId id
     * @return 菜单id
     */
    Long[] listMenuIdsByRoleId(Long menuId);

    /**
     * 更新角色菜单关联
     *
     * @param roleId  角色id
     * @param menuIds 角色关联菜单
     * @return 结果
     */
    int updateRoleMenuLink(Long roleId, Long[] menuIds);

    /**
     * 根据角色id获取部门id
     *
     * @param roleId id
     * @return 部门id
     */
    Long[] listDeptIdsByRoleId(Long roleId);

    /**
     * 更新角色部门关联
     *
     * @param roleId  角色id
     * @param deptIds 角色关联部门
     * @return 结果
     */
    int updateRoleDeptLink(Long roleId, Long[] deptIds);


}
