package com.swing.sky.common.utils;

/**
 * 管理管理员工具类
 *
 * @author swing
 */
public class AdminUtils {
    /**
     * 校验角色是否为管理员
     *
     * @param roleId 角色id
     * @return 结果
     */
    public static boolean isAdminRole(Long roleId) {
        return roleId != null && 1L == roleId;
    }

    /**
     * 校验角色是否为管理员
     *
     * @param roleIds 角色id数组
     * @return 结果
     */
    public static boolean isAdminRole(Long[] roleIds) {
        for (Long id : roleIds) {
            if (id != null && 1L == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验用户是否为管理员
     *
     * @param userId 用户id
     * @return 结果
     */
    public static boolean isAdminUser(Long userId) {
        return userId != null && 1L == userId;
    }

    /**
     * 校验用户是否为管理员
     *
     * @param userIds 用户id数组
     * @return 结果
     */
    public static boolean isAdminUser(Long[] userIds) {
        for (Long id : userIds) {
            if (id != null && 1L == id) {
                return true;
            }
        }
        return false;
    }
}
