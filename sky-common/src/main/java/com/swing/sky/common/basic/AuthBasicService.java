package com.swing.sky.common.basic;

import java.util.List;

/**
 * 服务层基本接口(需要根据权限限制访问的资源）
 *
 * @author swing
 */
public interface AuthBasicService<T> {
    /**
     * 插入
     *
     * @param t        内容
     * @param userId   用户id
     * @return 影响行数
     */
    int insert(T t, Long userId);

    /**
     * 删除
     *
     * @param id     主键
     * @param userId 用户id
     * @return 影响行数
     */
    int deleteById(Long id, Long userId);

    /**
     * 批量删除
     *
     * @param ids    需要删除的信息Id集合
     * @param userId 用户id
     * @return 结果
     */
    int batchDeleteByIds(Long[] ids, Long userId);

    /**
     * 更新
     *
     * @param t        内容
     * @param userId   用户id
     * @return 影响行数
     */
    int update(T t, Long userId);

    /**
     * 根据主键获取实体类
     *
     * @param id     主键
     * @param userId 用户id
     * @return 实体类
     */
    T getById(Long id, Long userId);

    /**
     * 查询符合条件的集合
     *
     * @param beginTime 开始时间
     * @param endTime   终止时间
     * @param t         条件
     * @param userId    用户id
     * @return 符合条件的集合
     */
    List<T> listByCondition(T t, String beginTime, String endTime, Long userId);
}
