package com.swing.sky.system.module.dao;

import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.system.module.domain.SysDictDataDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author swing
 * 筛选字段：
 * type_id:字典类型id
 * isDefault:是否为默认
 * use:是否使用
 * beginTime-endTime:记录创建时间
 */
public interface SysDictDataDAO extends BasicDAO<SysDictDataDO> {
    //--------------------------------唯一字段校验方法-----------------------------------------

    /**
     * 校验同一字典类型下是否有相同的数据key
     *
     * @param typeId  类型id
     * @param dataKey 数据key
     * @param id      数据id
     * @return 数量
     */
    int countByTypeIdAndDataKey(@Param("typeId") Long typeId, @Param("dataKey") String dataKey, @Param("id") Long id);
}