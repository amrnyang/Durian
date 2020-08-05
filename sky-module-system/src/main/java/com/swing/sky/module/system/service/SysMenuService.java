package com.swing.sky.module.system.service;

import com.swing.sky.common.basic.BasicService;
import com.swing.sky.module.system.domain.SysMenuDO;

import java.util.List;

/**
 * @author swing
 */
public interface SysMenuService extends BasicService<SysMenuDO> {
    /**
     * 在用户拥有的数据范围内查询信息列表
     *
     * @param userId    用户id
     * @param t         条件
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 结果
     */
    List<SysMenuDO> listByConditionAndUserId(Long userId, SysMenuDO t, String beginTime, String endTime);

}
