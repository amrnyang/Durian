package com.swing.sky.tiku.module.service.impl;

import com.swing.sky.common.annotation.SkyServiceAuthority;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.tiku.module.dao.TiCourseDAO;
import com.swing.sky.tiku.module.domain.TiCourseDO;
import com.swing.sky.tiku.module.service.TiCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表
 *
 * @author swing
 */
@Service
public class TiCourseServiceImpl implements TiCourseService {

    @Resource
    private TiCourseDAO tiCourseDAO;

    @Override
    @SkyServiceAuthority(moduleName = ModuleConstants.TIKU_COURSE, isAuth = false)
    public int insert(TiCourseDO tiCourseDO) {
        if (tiCourseDAO.countByCourseCode(tiCourseDO.getCourseCode(), -1L) > 0) {
            throw new RuntimeException("课程编码已存在");
        }
        return tiCourseDAO.insert(tiCourseDO);
    }

    @Override
    public int deleteById(Long id) {
        return tiCourseDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return tiCourseDAO.batchDeleteByIds(ids);
    }

    @Override
    @SkyServiceAuthority(moduleName = ModuleConstants.TIKU_COURSE, isAuth = false)
    public int update(TiCourseDO tiCourseDO) {
        if (tiCourseDAO.countByCourseCode(tiCourseDO.getCourseCode(), tiCourseDO.getId()) > 0) {
            throw new RuntimeException("课程编码已存在");
        }
        return tiCourseDAO.update(tiCourseDO);
    }

    @Override
    public TiCourseDO getById(Long id) {
        return tiCourseDAO.getById(id);
    }

    @Override
    public List<TiCourseDO> listByCondition(TiCourseDO tiCourseDO, String beginTime, String endTime) {
        return tiCourseDAO.listByCondition(tiCourseDO, beginTime, endTime);
    }

}