package com.swing.sky.tiku.module.service.impl;

import com.swing.sky.common.annotation.CurrentDataSource;
import com.swing.sky.common.enums.DataSourceEnum;
import com.swing.sky.tiku.module.dao.TiQuestionSubmitDAO;
import com.swing.sky.tiku.module.domain.TiQuestionSubmitDO;
import com.swing.sky.tiku.module.service.TiQuestionSubmitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表
 *
 * @author swing
 */
@Service
@CurrentDataSource(DataSourceEnum.TIKU)
public class TiQuestionSubmitServiceImpl implements TiQuestionSubmitService {

    @Resource
    private TiQuestionSubmitDAO tiQuestionSubmitDAO;

    @Override
    public int insert(TiQuestionSubmitDO tiQuestionSubmitDO) {
        return tiQuestionSubmitDAO.insert(tiQuestionSubmitDO);
    }

    @Override
    public int deleteById(Long id) {
        return tiQuestionSubmitDAO.deleteById(id);
    }

    @Override
    public int batchDeleteByIds(Long[] ids) {
        return tiQuestionSubmitDAO.batchDeleteByIds(ids);
    }

    @Override
    public int update(TiQuestionSubmitDO tiQuestionSubmitDO) {
        return tiQuestionSubmitDAO.update(tiQuestionSubmitDO);
    }

    @Override
    public TiQuestionSubmitDO getById(Long id) {
        return tiQuestionSubmitDAO.getById(id);
    }

    @Override
    public List<TiQuestionSubmitDO> listByCondition(TiQuestionSubmitDO tiQuestionSubmitDO, String beginTime, String endTime) {
        return tiQuestionSubmitDAO.listByCondition(tiQuestionSubmitDO, beginTime, endTime);
    }

}