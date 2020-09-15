package com.swing.sky.system.module.service.impl;

import com.swing.sky.system.module.service.SysLogLoginService;
import com.swing.sky.system.module.dao.SysLogLoginDAO;
import com.swing.sky.system.module.domain.SysLogLoginDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
public class SysLogLoginServiceImpl implements SysLogLoginService {
    private static final Logger log = LoggerFactory.getLogger(SysLogLoginServiceImpl.class);

    @Resource
    private SysLogLoginDAO logLoginDAO;

    @Override
    public int insert(SysLogLoginDO sysLogLoginDO) {
        return logLoginDAO.insert(sysLogLoginDO);
    }

    @Override
    public int deleteById(Long id) {
        return logLoginDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return logLoginDAO.batchDeleteByIds(ids);
    }

    @Override
    public int update(SysLogLoginDO sysLogLoginDO) {
        return logLoginDAO.update(sysLogLoginDO);
    }

    @Override
    public SysLogLoginDO getById(Long id) {
        return logLoginDAO.getById(id);
    }

    @Override
    public List<SysLogLoginDO> listByCondition(SysLogLoginDO sysLogLoginDO, String beginTime, String endTime) {
        return logLoginDAO.listByCondition(sysLogLoginDO, beginTime, endTime);
    }
}
