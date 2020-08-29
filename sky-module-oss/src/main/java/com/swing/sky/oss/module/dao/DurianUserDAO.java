package com.swing.sky.oss.module.dao;

import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.oss.module.domain.DurianUserDO;

/**
 * 用户信息表
 *
 * @author swing
 */
public interface DurianUserDAO extends BasicDAO<DurianUserDO> {
    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    DurianUserDO getUserByUsername(String username);
}