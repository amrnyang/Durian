package com.swing.sky.system.module.service.impl;

import com.swing.sky.system.module.annotation.SkyServiceAuthority;
import com.swing.sky.common.utils.AdminUtils;
import com.swing.sky.system.module.dao.SysPostDAO;
import com.swing.sky.system.module.domain.SysPostDO;
import com.swing.sky.system.module.service.SysPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
public class SysPostServiceImpl implements SysPostService {
    private static final Logger log = LoggerFactory.getLogger(SysPostServiceImpl.class);

    @Resource
    private SysPostDAO postDAO;

    @Override
    @SkyServiceAuthority(moduleName = "post")
    public int insert(SysPostDO sysPostDO) {
        if (postDAO.countByPostName(sysPostDO.getPostName(), -1L) > 0) {
            throw new RuntimeException("新增岗位信息失败,岗位名:" + sysPostDO.getPostName() + "已存在");
        }
        return postDAO.insert(sysPostDO);
    }

    @Override
    @SkyServiceAuthority(moduleName = "post")
    public int deleteById(Long id) {
        return postDAO.deleteById(id);
    }

    @Override
    @SkyServiceAuthority(moduleName = "post")
    public int batchDeleteByIds(Long[] ids) {
        return postDAO.batchDeleteByIds(ids);
    }

    @Override
    @SkyServiceAuthority(moduleName = "post")
    public int update(SysPostDO sysPostDO) {
        if (postDAO.countByPostName(sysPostDO.getPostName(), sysPostDO.getId()) > 0) {
            throw new RuntimeException("更新岗位信息失败,岗位名:" + sysPostDO.getPostName() + "已存在");
        }
        return postDAO.update(sysPostDO);
    }

    @Override
    @SkyServiceAuthority(moduleName = "post")
    public SysPostDO getById(Long id) {
        return postDAO.getById(id);
    }

    @Override
    public List<SysPostDO> listByCondition(SysPostDO sysPostDO, String beginTime, String endTime) {
        return postDAO.listByCondition(sysPostDO, beginTime, endTime);
    }

    @Override
    public List<SysPostDO> listByConditionAndUserId(Long userId, SysPostDO t, String beginTime, String endTime) {
        if (AdminUtils.isAdminUser(userId)) {
            return postDAO.listByCondition(t, beginTime, endTime);
        }
        return postDAO.listByConditionAndUserId(userId, t, beginTime, endTime);
    }
}
