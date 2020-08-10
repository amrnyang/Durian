package com.swing.sky.tiku.module.dao;

import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.tiku.module.domain.TiCourseDO;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息表
 *
 * @author swing
 */
public interface TiCourseDAO extends BasicDAO<TiCourseDO> {


    //--------------------------------唯一字段校验方法-----------------------------------------

    /**
     * 校验课程编码是否唯一
     *
     * @param courseCode 课程编码
     * @param id         课程ids
     * @return 数量
     */
    int countByCourseCode(@Param("courseCode") String courseCode, @Param("id") Long id);
}