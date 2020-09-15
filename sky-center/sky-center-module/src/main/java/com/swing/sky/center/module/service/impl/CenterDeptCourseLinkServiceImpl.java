package com.swing.sky.center.module.service.impl;

import com.swing.sky.center.module.dao.CenterDeptCourseLinkDAO;
import com.swing.sky.center.module.domain.CenterCourseDO;
import com.swing.sky.center.module.domain.CenterDeptCourseDO;
import com.swing.sky.center.module.domain.CenterDeptDO;
import com.swing.sky.center.module.service.CenterDeptCourseLinkService;
import com.swing.sky.common.annotation.CurrentDataSource;
import com.swing.sky.common.enums.DataSourceEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
@CurrentDataSource(DataSourceEnum.CENTER)
public class CenterDeptCourseLinkServiceImpl implements CenterDeptCourseLinkService {
    @Resource
    private CenterDeptCourseLinkDAO dao;

    @Override
    public int batchInsert(List<CenterDeptCourseDO> centerDeptCourseDOS) {
        return dao.batchInsert(centerDeptCourseDOS);
    }

    @Override
    public int deleteItemByOneId(Long id) {
        return dao.deleteItemByOneId(id);
    }

    @Override
    public int batchDeleteItemByOneIds(Long[] ids) {
        return dao.batchDeleteItemByOneIds(ids);
    }

    @Override
    public int deleteItemByTwoId(Long id) {
        return dao.deleteItemByTwoId(id);
    }

    @Override
    public int batchDeleteItemByTwoIds(Long[] ids) {
        return dao.batchDeleteItemByTwoIds(ids);
    }

    @Override
    public int countItemByOneId(Long id) {
        return dao.countItemByOneId(id);
    }

    @Override
    public int countItemByTwoId(Long id) {
        return dao.countItemByTwoId(id);
    }

    @Override
    public List<CenterDeptDO> listOneByTwoId(Long id) {
        return dao.listOneByTwoId(id);
    }

    @Override
    public List<CenterDeptDO> listOneByTwoIds(Long[] ids) {
        return dao.listOneByTwoIds(ids);
    }

    @Override
    public Long[] listOneIdsByTwoId(Long id) {
        return dao.listOneIdsByTwoId(id);
    }

    @Override
    public Long[] listOneIdsByTwoIds(Long[] ids) {
        return dao.listOneIdsByTwoIds(ids);
    }

    @Override
    public List<CenterCourseDO> listTwoByOneId(Long id) {
        return dao.listTwoByOneId(id);
    }

    @Override
    public List<CenterCourseDO> listTwoByOneIds(Long[] ids) {
        return dao.listTwoByOneIds(ids);
    }

    @Override
    public Long[] listTwoIdsByOneId(Long id) {
        return dao.listTwoIdsByOneId(id);
    }

    @Override
    public Long[] listTwoIdsByOneIds(Long[] ids) {
        return dao.listTwoIdsByOneIds(ids);
    }

    @Override
    public int insert(Long deptId, Long courseId) {
        return dao.insert(deptId, courseId);
    }

    @Override
    public int delete(Long deptId, Long courseId) {
        return dao.delete(deptId, courseId);
    }
}
