package com.swing.sky.tiku.module.service.impl;


import com.swing.sky.common.annotation.CurrentDataSource;
import com.swing.sky.common.enums.DataSourceEnum;
import com.swing.sky.tiku.module.dao.TiAnswerDAO;
import com.swing.sky.tiku.module.domain.TiAnswerDO;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import com.swing.sky.tiku.module.service.TiAnswerService;
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
public class TiAnswerServiceImpl implements TiAnswerService {

    @Resource
    private TiAnswerDAO tiAnswerDAO;

    @Override
    public int insert(TiAnswerDO tiAnswerDO) {
        tiAnswerDO.setCreatorId(1L);
        tiAnswerDO.setCreateBy("后台管理人员");
        tiAnswerDO.setUpdateBy("后台管理人员");
        tiAnswerDO.setCreateTime(new Date());
        tiAnswerDO.setUpdateTime(new Date());
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
        tiAnswerDO.setUpdateBy("后台管理人员");
        tiAnswerDO.setUpdateTime(new Date());
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

    /**
     * 依据题目数组获取所有的答案列表
     *
     * @param list 题目集合
     * @return 答案列表
     */
    @Override
    public List<TiAnswerDO> listAnswersByQuestions(List<TiQuestionDO> list) {
        //将题目列表转化为id数组
        Long[] ids = new Long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ids[i] = list.get(i).getId();
        }
        return tiAnswerDAO.listAnswersByQuestionIds(ids);
    }

    @Override
    public int countAnswerByQuestionId(Long questionId) {
        return tiAnswerDAO.countAnswerByQuestionId(questionId);
    }

}