package com.swing.sky.common.basic;

import java.util.List;

/**
 * 服务层基本接口
 *
 * @author swing
 */
public interface BasicService<T> {
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
     * 查询符合条件的集合（此方法只有管理员用户可以使用，可以没有限制地获取该资源的所有记录）
     * 入对资源的访问需要进行权限限制，请使用扩展的方法：
     * List<T> listByConditionAndUserId(Long userId, T t, String beginTime, String endTime);
     *
     * @param beginTime 开始时间
     * @param endTime   终止时间
     * @param t         条件
     * @return 符合条件的集合
     */
    List<T> listByCondition(T t, String beginTime, String endTime);
}
