package com.swing.sky.module.system.dao;

import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.module.system.domain.SysConfigDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author swing
 * 筛选字段：
 * configName:配置名
 * inner:是否内置
 * use:是否使用
 * beginTime-endTime:记录创建时间
 */
public interface SysConfigDAO extends BasicDAO<SysConfigDO> {
    //--------------------------------唯一字段校验方法-----------------------------------------

    /**
     * 校验配置名是否唯一
     *
     * @param configName 配置名
     * @param id         配置id
     * @return 数量
     */
    int countByConfigName(@Param("configName") String configName, @Param("id") Long id);

    /**
     * 校验配置key是否唯一
     *
     * @param configKey 配置key
     * @param id        配置id
     * @return 数量
     */
    int countByConfigKey(@Param("configKey") String configKey, @Param("id") Long id);
}