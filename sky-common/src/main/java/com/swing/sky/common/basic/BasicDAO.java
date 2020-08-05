package com.swing.sky.common.basic;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 基本 dao 方法
 *
 * @author swing
 */
public interface BasicDAO<T> {
    /**
     * 插入
     *
     * @param t 内容
     * @return 影响行数
     */
    int insert(T t);

    /**
     * 删除
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 批量删除
     *
     * @param ids 需要删除的信息Id集合
     * @return 结果
     */
    int batchDeleteByIds(Long[] ids);

    /**
     * 更新
     *
     * @param t 内容
     * @return 影响行数
     */
    int update(T t);

    /**
     * 根据主键获取实体类
     *
     * @param id 主键
     * @return 实体类
     */
    T getById(Long id);

    /**
     * 查询符合条件的集合
     *
     * @param beginTime 开始时间
     * @param endTime   终止时间
     * @param t         条件
     * @return 符合条件的集合
     */
    List<T> listByCondition(@Param("condition") T t, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
