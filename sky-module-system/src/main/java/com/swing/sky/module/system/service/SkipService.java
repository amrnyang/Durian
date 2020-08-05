package com.swing.sky.module.system.service;

import com.swing.sky.module.system.domain.*;

import java.util.List;

/**
 * 关联跳跃访问资源服务
 * 获取某个用户可访问的资源列表
 *
 * @author swing
 */
public interface SkipService {
    /**
     * 根据id获取用户集合
     *
     * @param userId 用户id
     * @return 集合
     */
    List<SysUserDO> getUserListByUserId(Long userId);

    /**
     * 根据用户id获取用户id数组
     *
     * @param userId 用户id
     * @return 数组
     */
    Long[] getUserIdsByUserId(Long userId);

    /**
     * 根据id获取角色集合
     *
     * @param userId 用户id
     * @return 集合
     */
    List<SysRoleDO> getRoleListByUserId(Long userId);

    /**
     * 根据用户id获取角色id数组
     *
     * @param userId 用户id
     * @return 数组
     */
    Long[] getRoleIdsByUserId(Long userId);

    /**
     * 根据id获取部门集合
     *
     * @param userId 用户id
     * @return 集合
     */
    List<SysDeptDO> getDeptListByUserId(Long userId);

    /**
     * 根据用户id获取部门id数组
     *
     * @param userId 用户id
     * @return 数组
     */
    Long[] getDeptIdsByUserId(Long userId);

    /**
     * 根据id获取菜单集合
     *
     * @param userId 用户id
     * @return 集合
     */
    List<SysMenuDO> getMenuListByUserId(Long userId);

    /**
     * 根据用户id获取菜单id数组
     *
     * @param userId 用户id
     * @return 数组
     */
    Long[] getMenuIdsByUserId(Long userId);

    /**
     * 根据id获取岗位集合
     *
     * @param userId 用户id
     * @return 集合
     */
    List<SysPostDO> getPostListByUserId(Long userId);

    /**
     * 根据用户id获取岗位id数组
     *
     * @param userId 用户id
     * @return 数组
     */
    Long[] getPostIdsByUserId(Long userId);
}
