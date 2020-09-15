package com.swing.sky.center.module.service.impl;

import com.swing.sky.center.module.domain.CenterCourseDO;
import com.swing.sky.center.module.dao.CenterCourseDAO;
import com.swing.sky.center.module.service.CenterCourseService;
import com.swing.sky.common.annotation.CurrentDataSource;
import com.swing.sky.common.enums.DataSourceEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 榴莲题库-课程表
 *
 * @author swing
 */
@Service
@CurrentDataSource(DataSourceEnum.CENTER)
public class CenterCourseServiceImpl implements CenterCourseService {

    @Resource
    private CenterCourseDAO centerCourseDAO;

    @Override
    public int insert(CenterCourseDO centerCourseDO) {
        return centerCourseDAO.insert(centerCourseDO);
    }

    @Override
    public int deleteById(Long id) {
        return centerCourseDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return centerCourseDAO.batchDeleteByIds(ids);
    }

    @Override
    public int update(CenterCourseDO centerCourseDO) {
        return centerCourseDAO.update(centerCourseDO);
    }

    @Override
    public CenterCourseDO getById(Long id) {
        return centerCourseDAO.getById(id);
    }

    @Override
    public List<CenterCourseDO> listByCondition(CenterCourseDO centerCourseDO, String beginTime, String endTime) {
        return centerCourseDAO.listByCondition(centerCourseDO, beginTime, endTime);
    }

}