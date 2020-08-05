package com.swing.sky.system.module.service.impl;

import com.swing.sky.common.annotation.SkyServiceAuthority;
import com.swing.sky.system.module.service.SysDictTypeService;
import com.swing.sky.system.module.dao.SysDictTypeDAO;
import com.swing.sky.system.module.domain.SysDictTypeDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {
    private static final Logger log = LoggerFactory.getLogger(SysDictTypeServiceImpl.class);

    @Resource
    private SysDictTypeDAO dictTypeDAO;

    @Override
    @SkyServiceAuthority(moduleName = "dictType")
    public int insert(SysDictTypeDO sysDictTypeDO) {
        if (dictTypeDAO.countByTypeSign(sysDictTypeDO.getTypeSign(), -1L) > 0) {
            throw new RuntimeException("新增字典类型失败，类型标识已存在");
        }
        if (dictTypeDAO.countByTypeName(sysDictTypeDO.getTypeName(), -1L) > 0) {
            throw new RuntimeException("新增字典类型失败，类型名称已存在");
        }
        return dictTypeDAO.insert(sysDictTypeDO);
    }

    @Override
    public int deleteById(Long id) {
        return dictTypeDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return dictTypeDAO.batchDeleteByIds(ids);
    }

    @Override
    @SkyServiceAuthority(moduleName = "dictType")
    public int update(SysDictTypeDO sysDictTypeDO) {
        if (dictTypeDAO.countByTypeSign(sysDictTypeDO.getTypeSign(), sysDictTypeDO.getId()) > 0) {
            throw new RuntimeException("更新字典类型失败，类型标识已存在");
        }
        if (dictTypeDAO.countByTypeName(sysDictTypeDO.getTypeName(), sysDictTypeDO.getId()) > 0) {
            throw new RuntimeException("更新字典类型失败，类型名称已存在");
        }
        return dictTypeDAO.update(sysDictTypeDO);
    }

    @Override
    public SysDictTypeDO getById(Long id) {
        return dictTypeDAO.getById(id);
    }

    @Override
    public List<SysDictTypeDO> listByCondition(SysDictTypeDO sysDictTypeDO, String beginTime, String endTime) {
        return dictTypeDAO.listByCondition(sysDictTypeDO, beginTime, endTime);
    }
}
