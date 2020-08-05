package com.swing.sky.module.system.service;

import com.swing.sky.common.basic.BasicService;
import com.swing.sky.module.system.domain.SysPostDO;

import java.util.List;

/**
 * @author swing
 */
public interface SysPostService extends BasicService<SysPostDO> {
    /**
     * 在用户拥有的数据范围内查询信息列表
     *
     * @param userId    用户id
     * @param t         条件
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 结果
     */
    List<SysPostDO> listByConditionAndUserId(Long userId, SysPostDO t, String beginTime, String endTime);
}