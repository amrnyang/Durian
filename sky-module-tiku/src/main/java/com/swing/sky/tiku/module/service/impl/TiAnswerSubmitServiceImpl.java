package com.swing.sky.tiku.module.service.impl;

import com.swing.sky.tiku.module.dao.TiAnswerSubmitDAO;
import com.swing.sky.tiku.module.domain.TiAnswerSubmitDO;
import com.swing.sky.tiku.module.service.TiAnswerSubmitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表
 *
 * @author swing
 */
@Service
public class TiAnswerSubmitServiceImpl implements TiAnswerSubmitService {

    @Resource
    private TiAnswerSubmitDAO tiAnswerSubmitDAO;

    @Override
    public int insert(TiAnswerSubmitDO tiAnswerSubmitDO) {
        return tiAnswerSubmitDAO.insert(tiAnswerSubmitDO);
    }

    @Override
    public int deleteById(Long id) {
        return tiAnswerSubmitDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return tiAnswerSubmitDAO.batchDeleteByIds(ids);
    }

    @Override
    public int update(TiAnswerSubmitDO tiAnswerSubmitDO) {
        return tiAnswerSubmitDAO.update(tiAnswerSubmitDO);
    }

    @Override
    public TiAnswerSubmitDO getById(Long id) {
        return tiAnswerSubmitDAO.getById(id);
    }

    @Override
    public List<TiAnswerSubmitDO> listByCondition(TiAnswerSubmitDO tiAnswerSubmitDO, String beginTime, String endTime) {
        return tiAnswerSubmitDAO.listByCondition(tiAnswerSubmitDO, beginTime, endTime);
    }

}