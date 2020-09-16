package com.swing.sky.common.basic;

import java.util.List;

/**
 * 关联表的基本方法
 *
 * @author swing
 */
public interface BasicLinkService<One, Two, Item> {
    /**
     * 批量插入信息
     *
     * @param items 信息集合
     * @return 影响行数
     */
    int batchInsert(List<Item> items);

    /**
     * 根据One的id 删除T
     *
     * @param id One的id
     * @return 影响行数
     */
    int deleteItemByOneId(Long id);

    /**
     * 根据One的id 批量删除T
     *
     * @param ids id数组
     * @return 影响行数
     */
    int batchDeleteItemByOneIds(Long[] ids);

    /**
     * 根据Two的id 删除T
     *
     * @param id Two的id
     * @return 影响行数
     */
    int deleteItemByTwoId(Long id);


    /**
     * 根据Two的id 批量删除T
     *
     * @param ids id数组
     * @return 影响行数
     */
    int batchDeleteItemByTwoIds(Long[] ids);

    /**
     * 根据One的id统计数据量
     *
     * @param id One的id
     * @return 数量
     */
    int countItemByOneId(Long id);

    /**
     * 根据Two的id统计数据量
     *
     * @param id Two的id
     * @return 数量
     */
    int countItemByTwoId(Long id);

    /**
     * 根据Two的id列出One的信息列表
     *
     * @param id Two的Id
     * @return 信息列表
     */
    List<One> listOneByTwoId(Long id);

    /**
     * 根据Two的ids列出One的信息列表(去重复）
     *
     * @param ids Two的Ids
     * @return 信息列表
     */
    List<One> listOneByTwoIds(Long[] ids);

    /**
     * 根据Two的id列出One的id数组
     *
     * @param id Two的Id
     * @return 信息列表
     */
    Long[] listOneIdsByTwoId(Long id);

    /**
     * 根据Two的ids列出One的id数组(去重复）
     *
     * @param ids Two的Ids
     * @return 信息列表
     */
    Long[] listOneIdsByTwoIds(Long[] ids);


    /**
     * 根据Two的id列出One的信息列表
     *
     * @param id Two的Id
     * @return 信息列表
     */
    List<Two> listTwoByOneId(Long id);

    /**
     * 根据Two的id列出One的信息列表(去重复）
     *
     * @param ids Two的Ids
     * @return 信息列表
     */
    List<Two> listTwoByOneIds(Long[] ids);

    /**
     * 根据Two的id列出One的idid数组
     *
     * @param id Two的Id
     * @return 信息列表
     */
    Long[] listTwoIdsByOneId(Long id);

    /**
     * 根据Two的id列出One的id数组(去重复）
     *
     * @param ids Two的Ids
     * @return 信息列表
     */
    Long[] listTwoIdsByOneIds(Long[] ids);
}
