package com.swing.sky.oss.module.service.impl;

import com.swing.sky.oss.module.domain.DurianUserDO;
import com.swing.sky.oss.module.dao.DurianUserDAO;
import com.swing.sky.oss.module.service.DurianUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表
 *
 * @author JIANG
 */
@Service
public class DurianUserServiceImpl implements DurianUserService {

    @Resource
    private DurianUserDAO durianUserDAO;

    @Override
    public int insert(DurianUserDO durianUserDO) {
        return durianUserDAO.insert(durianUserDO);
    }

    @Override
    public int deleteById(Long id) {
        return durianUserDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return durianUserDAO.batchDeleteByIds(ids);
    }

    @Override
    public int update(DurianUserDO durianUserDO) {
        return durianUserDAO.update(durianUserDO);
    }

    @Override
    public DurianUserDO getById(Long id) {
        return durianUserDAO.getById(id);
    }

    @Override
    public List<DurianUserDO> listByCondition(DurianUserDO durianUserDO, String beginTime, String endTime) {
        return durianUserDAO.listByCondition(durianUserDO, beginTime, endTime);
    }

    @Override
    public DurianUserDO getUserByUsername(String username) {
        return durianUserDAO.getUserByUsername(username);
    }
}