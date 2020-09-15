package com.swing.sky.center.module.dao;

import com.swing.sky.center.module.domain.CenterUserDO;
import com.swing.sky.common.basic.BasicDAO;

/**
 * 会员信息表
 *
 * @author swing
 */
public interface CenterUserDAO extends BasicDAO<CenterUserDO> {
    /**
     * 依据用户名获取用户信息
     *
     * @param username 用户名
     * @return 返回
     */
    CenterUserDO getUserByUsername(String username);
}