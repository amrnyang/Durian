package com.swing.sky.tiku.module.dao;

import com.swing.sky.common.basic.BasicLinkDAO;
import com.swing.sky.system.module.domain.SysDeptDO;
import com.swing.sky.tiku.module.domain.TiCourseDO;
import com.swing.sky.tiku.module.domain.TiDeptCourseDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author swing
 */
public interface TiDeptCourseLinkDAO extends BasicLinkDAO<SysDeptDO, TiCourseDO, TiDeptCourseDO> {
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