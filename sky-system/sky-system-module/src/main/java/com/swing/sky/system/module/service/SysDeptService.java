package com.swing.sky.system.module.service;

import com.swing.sky.common.basic.BasicService;
import com.swing.sky.system.module.domain.SysDeptDO;

import java.util.List;

/**
 * @author swing
 */
public interface SysDeptService extends BasicService<SysDeptDO> {
    /**
     * 在用户拥有的数据范围内查询信息列表
     *
     * @param userId    用户id
     * @param t         条件
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 结果
     */
    List<SysDeptDO> listByConditionAndUserId(Long userId, SysDeptDO t, String beginTime, String endTime);

    /**
     * 获取该部门的所有岗位id
     *
     * @param deptId 部门id
     * @return 岗位列表
     */
    Long[] listPostIdsByDeptId(Long deptId);

    /**
     * 更新岗位部门关联
     *
     * @param deptId  部门id
     * @param postIds 岗位id
     * @return 结果
     */
    int updateDeptPostLink(Long deptId, Long[] postIds);
}
