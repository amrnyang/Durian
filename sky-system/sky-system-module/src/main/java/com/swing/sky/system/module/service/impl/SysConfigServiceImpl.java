package com.swing.sky.system.module.service.impl;

import com.swing.sky.system.module.annotation.SkyServiceAuthority;
import com.swing.sky.system.module.service.SysConfigService;
import com.swing.sky.system.module.dao.SysConfigDAO;
import com.swing.sky.system.module.domain.SysConfigDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    private static final Logger log = LoggerFactory.getLogger(SysConfigServiceImpl.class);

    @Resource
    private SysConfigDAO configDAO;

    @Override
    @SkyServiceAuthority(moduleName = "config")
    public int insert(SysConfigDO sysConfigDO) {
        if (configDAO.countByConfigKey(sysConfigDO.getConfigKey(), -1L) > 0) {
            throw new RuntimeException("新增失败，配置键已存在");
        }
        if (configDAO.countByConfigName(sysConfigDO.getConfigName(), -1L) > 0) {
            throw new RuntimeException("新增失败，配置名已存在");
        }
        return configDAO.insert(sysConfigDO);
    }

    @Override
    public int deleteById(Long id) {
        return configDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return configDAO.batchDeleteByIds(ids);
    }

    @Override
    @SkyServiceAuthority(moduleName = "config")
    public int update(SysConfigDO sysConfigDO) {
        if (configDAO.countByConfigKey(sysConfigDO.getConfigKey(), sysConfigDO.getId()) > 0) {
            throw new RuntimeException("更新失败，配置键已存在");
        }
        if (configDAO.countByConfigName(sysConfigDO.getConfigName(), sysConfigDO.getId()) > 0) {
            throw new RuntimeException("更新失败，配置名已存在");
        }
        return configDAO.update(sysConfigDO);
    }

    @Override
    public SysConfigDO getById(Long id) {
        return configDAO.getById(id);
    }

    @Override
    public List<SysConfigDO> listByCondition(SysConfigDO sysConfigDO, String beginTime, String endTime) {
        return configDAO.listByCondition(sysConfigDO, beginTime, endTime);
    }
}
