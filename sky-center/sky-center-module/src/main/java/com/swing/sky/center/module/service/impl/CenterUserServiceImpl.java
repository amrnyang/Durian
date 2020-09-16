package com.swing.sky.center.module.service.impl;

import com.swing.sky.center.module.domain.CenterUserDO;
import com.swing.sky.center.module.dao.CenterUserDAO;
import com.swing.sky.center.module.service.CenterUserService;
import com.swing.sky.common.annotation.CurrentDataSource;
import com.swing.sky.common.enums.DataSourceEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员信息表
 *
 * @author swing
 */
@Service
@CurrentDataSource(DataSourceEnum.CENTER)
public class CenterUserServiceImpl implements CenterUserService {

    @Resource
    private CenterUserDAO centerUserDAO;

    @Override
    public int insert(CenterUserDO centerUserDO) {
        return centerUserDAO.insert(centerUserDO);
    }

    @Override
    public int deleteById(Long id) {
        return centerUserDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return centerUserDAO.batchDeleteByIds(ids);
    }

    @Override
    public int update(CenterUserDO centerUserDO) {
        return centerUserDAO.update(centerUserDO);
    }

    @Override
    public CenterUserDO getById(Long id) {
        return centerUserDAO.getById(id);
    }

    @Override
    public List<CenterUserDO> listByCondition(CenterUserDO centerUserDO, String beginTime, String endTime) {
        return centerUserDAO.listByCondition(centerUserDO, beginTime, endTime);
    }

    @Override
    public CenterUserDO getUserByUsername(String username) {
        return centerUserDAO.getUserByUsername(username);
    }
}