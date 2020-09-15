package com.swing.sky.center.module.service;

import com.swing.sky.center.module.domain.CenterUserDO;
import com.swing.sky.common.basic.BasicService;

/**
 * 会员信息表
 *
 * @author swing
 */
public interface CenterUserService extends BasicService<CenterUserDO> {
    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    CenterUserDO getUserByUsername(String username);
}