package com.swing.sky.system.module.service.impl;

import com.swing.sky.system.module.dao.SysUserOnlineDAO;
import com.swing.sky.system.module.domain.SysUserOnlineDO;
import com.swing.sky.system.module.service.SysUserOnlineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
public class SysUserOnlineServiceImpl implements SysUserOnlineService {
    private static final Logger log = LoggerFactory.getLogger(SysUserOnlineServiceImpl.class);

    @Resource
    private SysUserOnlineDAO userOnlineDAO;

    @Override
    public int insert(SysUserOnlineDO sysUserOnlineDO) {
        if (userOnlineDAO.countBySessionId(sysUserOnlineDO.getSessionId(), -1L) > 0) {
            throw new RuntimeException("该session已存在");
        }
        return userOnlineDAO.insert(sysUserOnlineDO);
    }

    @Override
    public int deleteById(Long id) {
        return userOnlineDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return userOnlineDAO.batchDeleteByIds(ids);
    }

    @Override
    public int update(SysUserOnlineDO sysUserOnlineDO) {
        if (userOnlineDAO.countBySessionId(sysUserOnlineDO.getSessionId(), sysUserOnlineDO.getId()) > 0) {
            throw new RuntimeException("该session已存在");
        }
        return userOnlineDAO.update(sysUserOnlineDO);
    }

    @Override
    public SysUserOnlineDO getById(Long id) {
        return userOnlineDAO.getById(id);
    }

    @Override
    public List<SysUserOnlineDO> listByCondition(SysUserOnlineDO sysUserOnlineDO, String beginTime, String endTime) {
        return userOnlineDAO.listByCondition(sysUserOnlineDO, beginTime, endTime);
    }
}
