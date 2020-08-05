package com.swing.sky.system.framework.security.utils;

import com.swing.sky.system.framework.security.service.UserDetailsImpl;
import com.swing.sky.system.module.domain.SysUserDO;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 从认证中获取用户信息
 *
 * @author swing
 */
public class UserDetailsUtil {
    /**
     * 获取当前认证的用户信息
     *
     * @return 用户信息
     */
    public static SysUserDO getUserDO() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUserDO();
    }

    /**
     * 获取当前认证的用户名
     *
     * @return 用户名
     */
    public static String getUsername() {
        return getUserDO().getUsername();
    }

    /**
     * 获取当前用户id
     * @return id
     */
    public static Long getUserId() {
        return getUserDO().getId();
    }
}
