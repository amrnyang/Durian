package com.swing.sky.system.module.service;

import com.swing.sky.common.basic.BasicService;
import com.swing.sky.system.module.domain.SysDictDataDO;

import java.util.List;

/**
 * @author swing
 */
public interface SysDictDataService extends BasicService<SysDictDataDO> {
    /**
     * 根据字典类型标识，列出字典数据
     *
     * @param typeSign 类型标识
     * @return 字典数据列表
     */
    List<SysDictDataDO> listDictDataByTypeSign(String typeSign);
}
