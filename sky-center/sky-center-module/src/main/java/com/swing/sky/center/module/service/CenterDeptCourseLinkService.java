package com.swing.sky.center.module.service;

import com.swing.sky.center.module.domain.CenterCourseDO;
import com.swing.sky.center.module.domain.CenterDeptCourseDO;
import com.swing.sky.center.module.domain.CenterDeptDO;
import com.swing.sky.common.basic.BasicLinkService;
import org.apache.ibatis.annotations.Param;

/**
 * @author swing
 */
public interface CenterDeptCourseLinkService extends BasicLinkService<CenterDeptDO, CenterCourseDO, CenterDeptCourseDO> {
    /**
     * 插入一个关联信息
     *
     * @param deptId   专业id
     * @param courseId 课程id
     * @return 结果
     */
    int insert(Long deptId, Long courseId);

    /**
     * 删除一个关联信息
     *
     * @param deptId   专业id
     * @param courseId 课程id
     * @return 结果
     */
    int delete(Long deptId, Long courseId);
}
