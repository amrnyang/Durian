package com.swing.sky.tiku.module.service.impl;


import com.swing.sky.tiku.module.dao.TiQuestionDAO;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import com.swing.sky.tiku.module.service.TiQuestionService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表
 *
 * @author swing
 */
public class TiQuestionServiceImpl implements TiQuestionService {

    @Resource
    private TiQuestionDAO tiQuestionDAO;

    @Override
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

}