package com.swing.sky.system.module.dao;

import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.system.module.domain.SysDictTypeDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author swing
 * 筛选字段：
 * typeName:类型名称
 * use:是否使用
 * beginTime-endTime:记录创建时间
 */
public interface SysDictTypeDAO extends BasicDAO<SysDictTypeDO> {
    /**
     * 根据类型标识获取字典类型
     *
     * @param typeSign 类型标识
     * @return 结果
     */
    SysDictTypeDO getByTypeSign(String typeSign);
    //--------------------------------唯一字段校验方法-----------------------------------------

    /**
     * 校验字典类型名称是否唯一
     *
     * @param typeName 字典类型名称
     * @param id       字典类型id
     * @return 数量
     */
    int countByTypeName(@Param("typeName") String typeName, @Param("id") Long id);

    /**
     * 校验字典类型标识是否唯一
     *
     * @param typeSign 字典类型标识
     * @param id       字典类型id
     * @return 数量
     */
    int countByTypeSign(@Param("typeSign") String typeSign, @Param("id") Long id);
}