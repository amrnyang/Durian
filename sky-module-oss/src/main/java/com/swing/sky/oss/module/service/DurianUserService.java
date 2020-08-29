package com.swing.sky.oss.module.service;

import com.swing.sky.common.basic.BasicService;
import com.swing.sky.oss.module.domain.DurianUserDO;

/**
 * 用户信息表
 *
 * @author swing
 */
public interface DurianUserService extends BasicService<DurianUserDO> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    DurianUserDO getUserByUsername(String username);
}