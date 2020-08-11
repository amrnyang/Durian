package com.swing.sky.tiku.module.service.impl;


import com.swing.sky.common.annotation.SkyServiceAuthority;
import com.swing.sky.common.constant.ModuleConstants;
import com.swing.sky.tiku.module.dao.TiQuestionDAO;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import com.swing.sky.tiku.module.service.TiQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表
 *
 * @author swing
 */
@Service
public class TiQuestionServiceImpl implements TiQuestionService {

    @Resource
    private TiQuestionDAO tiQuestionDAO;

    @Override
    @SkyServiceAuthority(moduleName = ModuleConstants.TIKU_QUESTION, isAuth = false)
    public int insert(TiQuestionDO tiQuestionDO) {
        return tiQuestionDAO.insert(tiQuestionDO);
    }

    @Override
    public int deleteById(Long id) {
        return tiQuestionDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return tiQuestionDAO.batchDeleteByIds(ids);
    }

    @Override
    @SkyServiceAuthority(moduleName = ModuleConstants.TIKU_QUESTION, isAuth = false)
    public int update(TiQuestionDO tiQuestionDO) {
        return tiQuestionDAO.update(tiQuestionDO);
    }

    @Override
    public TiQuestionDO getById(Long id) {
        return tiQuestionDAO.getById(id);
    }

    @Override
    public List<TiQuestionDO> listByCondition(TiQuestionDO tiQuestionDO, String beginTime, String endTime) {
        return tiQuestionDAO.listByCondition(tiQuestionDO, beginTime, endTime);
    }

    @Override
    public List<TiQuestionDO> listQuestionByCourseIds(Long[] courseIds) {
        return tiQuestionDAO.listQuestionByCourseIds(courseIds);
    }
}