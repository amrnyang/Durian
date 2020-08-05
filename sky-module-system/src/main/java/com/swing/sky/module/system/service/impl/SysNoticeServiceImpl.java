package com.swing.sky.module.system.service.impl;

import com.swing.sky.common.annotation.SkyServiceAuthority;
import com.swing.sky.module.system.service.SysNoticeService;
import com.swing.sky.module.system.dao.SysNoticeDAO;
import com.swing.sky.module.system.domain.SysNoticeDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {
    private static final Logger log = LoggerFactory.getLogger(SysNoticeServiceImpl.class);

    @Resource
    private SysNoticeDAO noticeDAO;

    @Override
    @SkyServiceAuthority(moduleName = "notice")
    public int insert(SysNoticeDO sysNoticeDO) {
        return noticeDAO.insert(sysNoticeDO);
    }

    @Override
    public int deleteById(Long id) {
        return noticeDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return noticeDAO.batchDeleteByIds(ids);
    }

    @Override
    @SkyServiceAuthority(moduleName = "notice")
    public int update(SysNoticeDO sysNoticeDO) {
        return noticeDAO.update(sysNoticeDO);
    }

    @Override
    public SysNoticeDO getById(Long id) {
        return noticeDAO.getById(id);
    }

    @Override
    public List<SysNoticeDO> listByCondition(SysNoticeDO sysNoticeDO, String beginTime, String endTime) {
        return noticeDAO.listByCondition(sysNoticeDO, beginTime, endTime);
    }
}
