package com.swing.sky.tiku.module.service.impl;


import com.swing.sky.common.annotation.CurrentDataSource;
import com.swing.sky.common.enums.DataSourceEnum;
import com.swing.sky.tiku.module.dao.TiQuestionDAO;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import com.swing.sky.tiku.module.service.TiQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户信息表
 *
 * @author swing
 */
@Service
@CurrentDataSource(DataSourceEnum.TIKU)
public class TiQuestionServiceImpl implements TiQuestionService {

    @Resource
    private TiQuestionDAO tiQuestionDAO;

    @Override
    public int insert(TiQuestionDO tiQuestionDO) {
        tiQuestionDO.setCreatorId(1L);
        tiQuestionDO.setUpdateBy("后台管理人员");
        tiQuestionDO.setCreateBy("后台管理人员");
        tiQuestionDO.setUpdateTime(new Date());
        tiQuestionDO.setCreateTime(new Date());
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
        tiQuestionDO.setUpdateBy("后台管理人员");
        tiQuestionDO.setUpdateTime(new Date());
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