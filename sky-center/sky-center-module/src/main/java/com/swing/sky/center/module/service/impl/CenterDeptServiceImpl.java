package com.swing.sky.center.module.service.impl;

import com.swing.sky.center.module.domain.CenterDeptDO;
import com.swing.sky.center.module.dao.CenterDeptDAO;
import com.swing.sky.center.module.service.CenterDeptService;
import com.swing.sky.common.annotation.CurrentDataSource;
import com.swing.sky.common.enums.DataSourceEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门表
 *
 * @author swing
 */
@Service
@CurrentDataSource(DataSourceEnum.CENTER)
public class CenterDeptServiceImpl implements CenterDeptService {

    @Resource
    private CenterDeptDAO centerDeptDAO;

    @Override
    public int insert(CenterDeptDO centerDeptDO) {
        return centerDeptDAO.insert(centerDeptDO);
    }

    @Override
    public int deleteById(Long id) {
        return centerDeptDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return centerDeptDAO.batchDeleteByIds(ids);
    }

    @Override
    public int update(CenterDeptDO centerDeptDO) {
        return centerDeptDAO.update(centerDeptDO);
    }

    @Override
    public CenterDeptDO getById(Long id) {
        return centerDeptDAO.getById(id);
    }

    @Override
    public List<CenterDeptDO> listByCondition(CenterDeptDO centerDeptDO, String beginTime, String endTime) {
        return centerDeptDAO.listByCondition(centerDeptDO, beginTime, endTime);
    }

}