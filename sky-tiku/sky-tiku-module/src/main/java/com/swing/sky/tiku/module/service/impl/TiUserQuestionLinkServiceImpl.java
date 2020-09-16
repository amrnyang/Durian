package com.swing.sky.tiku.module.service.impl;

import com.swing.sky.common.annotation.CurrentDataSource;
import com.swing.sky.common.enums.DataSourceEnum;
import com.swing.sky.tiku.module.dao.TiUserQuestionLinkDAO;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import com.swing.sky.tiku.module.service.TiUserQuestionLinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
@CurrentDataSource(DataSourceEnum.TIKU)
public class TiUserQuestionLinkServiceImpl implements TiUserQuestionLinkService {
    @Resource
    private TiUserQuestionLinkDAO dao;

    @Override
    public int insert(Long userId, Long questionId) {
        return dao.insert(userId, questionId);
    }

    @Override
    public List<TiQuestionDO> listQuestionByUserId(Long id) {
        return dao.listQuestionByUserId(id);
    }

    @Override
    public boolean isCollection(Long userId, Long questionId) {
        return dao.isCollection(userId, questionId) > 0;
    }
}
