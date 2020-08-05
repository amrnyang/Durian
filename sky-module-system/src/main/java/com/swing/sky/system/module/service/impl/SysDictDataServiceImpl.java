package com.swing.sky.system.module.service.impl;

import com.swing.sky.common.annotation.SkyServiceAuthority;
import com.swing.sky.system.module.service.SysDictDataService;
import com.swing.sky.system.module.dao.SysDictDataDAO;
import com.swing.sky.system.module.dao.SysDictTypeDAO;
import com.swing.sky.system.module.domain.SysDictDataDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
public class SysDictDataServiceImpl implements SysDictDataService {
    private static final Logger log = LoggerFactory.getLogger(SysDictDataServiceImpl.class);

    @Resource
    private SysDictTypeDAO dictTypeDAO;

    @Resource
    private SysDictDataDAO dictDataDAO;

    @Override
    @SkyServiceAuthority(moduleName = "dictData")
    public int insert(SysDictDataDO sysDictDataDO) {
        if (dictDataDAO.countByTypeIdAndDataKey(sysDictDataDO.getTypeId(), sysDictDataDO.getDataKey(), -1L) > 0) {
            throw new RuntimeException("新增失败，该字典类型下已经有该字典数据");
        }
        return dictDataDAO.insert(sysDictDataDO);
    }

    @Override
    public int deleteById(Long id) {
        return dictDataDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return dictDataDAO.batchDeleteByIds(ids);
    }

    @Override
    @SkyServiceAuthority(moduleName = "dictData")
    public int update(SysDictDataDO sysDictDataDO) {
        if (dictDataDAO.countByTypeIdAndDataKey(sysDictDataDO.getTypeId(), sysDictDataDO.getDataKey(), sysDictDataDO.getId()) > 0) {
            throw new RuntimeException("更新失败，该字典类型下已经有该字典数据");
        }
        return dictDataDAO.update(sysDictDataDO);
    }

    @Override
    public SysDictDataDO getById(Long id) {
        return dictDataDAO.getById(id);
    }

    @Override
    public List<SysDictDataDO> listByCondition(SysDictDataDO sysDictDataDO, String beginTime, String endTime) {
        return dictDataDAO.listByCondition(sysDictDataDO, beginTime, endTime);
    }

    @Override
    public List<SysDictDataDO> listDictDataByTypeSign(String typeSign) {
        SysDictDataDO dictDataDO = new SysDictDataDO();
        dictDataDO.setTypeId(dictTypeDAO.getByTypeSign(typeSign).getId());
        return dictDataDAO.listByCondition(dictDataDO, null, null);
    }
}
