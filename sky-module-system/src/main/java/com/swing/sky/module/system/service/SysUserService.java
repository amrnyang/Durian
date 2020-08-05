package com.swing.sky.module.system.service;

import com.swing.sky.common.basic.BasicService;
import com.swing.sky.module.system.domain.SysUserDO;

import java.util.List;

/**
 * @author swing
 */
public interface SysUserService extends BasicService<SysUserDO> {
    /**
     * 才用户拥有的数据范围内查询信息列表
     *
     * @param userId    用户id
     * @param t         条件
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 结果
     */
    List<SysUserDO> listByConditionAndUserId(Long userId, SysUserDO t, String beginTime, String endTime);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysUserDO getUserByUsername(String username);

    /**
     * 更新用户岗位关联
     *
     * @param userId  用户id
     * @param postIds 岗位ids
     * @return 结果
     */
    int updateUserPostLink(Long userId, Long[] postIds);

    /**
     * 更新用户角色关联
     *
     * @param userId  用户id
     * @param roleIds 角色ids
     * @return 结果
     */
    int updateUserRoleLink(Long userId, Long[] roleIds);
}






































