package com.swing.sky.system.module.service.impl;

import com.swing.sky.system.module.service.SysLogOperateService;
import com.swing.sky.system.module.dao.SysLogOperateDAO;
import com.swing.sky.system.module.domain.SysLogOperateDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
public class SysLogOperateServiceImpl implements SysLogOperateService {
    private static final Logger log = LoggerFactory.getLogger(SysLogOperateServiceImpl.class);

    @Resource
    private SysLogOperateDAO logOperateDAO;

    @Override
    public int insert(SysLogOperateDO sysLogOperateDO) {
        return logOperateDAO.insert(sysLogOperateDO);
    }

    @Override
    public int deleteById(Long id) {
        return logOperateDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return logOperateDAO.batchDeleteByIds(ids);
    }

    @Override
    public int update(SysLogOperateDO sysLogOperateDO) {
        return logOperateDAO.update(sysLogOperateDO);
    }

    @Override
    public SysLogOperateDO getById(Long id) {
        return logOperateDAO.getById(id);
    }

    @Override
    public List<SysLogOperateDO> listByCondition(SysLogOperateDO sysLogOperateDO, String beginTime, String endTime) {
        return logOperateDAO.listByCondition(sysLogOperateDO, beginTime, endTime);
    }
}
