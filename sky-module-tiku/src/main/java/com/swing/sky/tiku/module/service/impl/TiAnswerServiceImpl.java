package com.swing.sky.tiku.module.service.impl;


import com.swing.sky.tiku.module.dao.TiAnswerDAO;
import com.swing.sky.tiku.module.domain.TiAnswerDO;
import com.swing.sky.tiku.module.service.TiAnswerService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表
 *
 * @author swing
 */
public class TiAnswerServiceImpl implements TiAnswerService {

    @Resource
    private TiAnswerDAO tiAnswerDAO;

    @Override
    public int insert(TiAnswerDO tiAnswerDO) {
        return tiAnswerDAO.insert(tiAnswerDO);
    }

    @Override
    public int deleteById(Long id) {
        return tiAnswerDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return tiAnswerDAO.batchDeleteByIds(ids);
    }

    @Override
    public int update(TiAnswerDO tiAnswerDO) {
        return tiAnswerDAO.update(tiAnswerDO);
    }

    @Override
    public TiAnswerDO getById(Long id) {
        return tiAnswerDAO.getById(id);
    }

    @Override
    public List<TiAnswerDO> listByCondition(TiAnswerDO tiAnswerDO, String beginTime, String endTime) {
        return tiAnswerDAO.listByCondition(tiAnswerDO, beginTime, endTime);
    }

}