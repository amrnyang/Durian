package com.swing.sky.center.module.dao;

import com.swing.sky.center.module.domain.CenterDeptDO;
import com.swing.sky.center.module.domain.CenterCourseDO;
import com.swing.sky.center.module.domain.CenterDeptCourseDO;
import com.swing.sky.common.basic.BasicLinkDAO;
import org.apache.ibatis.annotations.Param;

/**
 * @author swing
 */
public interface CenterDeptCourseLinkDAO extends BasicLinkDAO<CenterDeptDO, CenterCourseDO, CenterDeptCourseDO> {
    /**
     * 插入一个关联信息
     * @param deptId 专业id
     * @param courseId 课程id
     * @return 结果
     */
    int insert(@Param("deptId") Long deptId, @Param("courseId") Long courseId);

    /**
     * 删除一个关联信息
     * @param deptId 专业id
     * @param courseId 课程id
     * @return 结果
     */
    int delete(@Param("deptId") Long deptId, @Param("courseId") Long courseId);
}