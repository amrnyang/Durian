package com.swing.sky.es.module.service.impl;

import com.swing.sky.center.module.service.CenterCourseService;
import com.swing.sky.common.annotation.CurrentDataSource;
import com.swing.sky.common.enums.DataSourceEnum;
import com.swing.sky.es.module.dao.TiQuestionSearchDAO;
import com.swing.sky.es.module.domain.TiQuestionSearchDO;
import com.swing.sky.es.module.service.TiQuestionSearchService;
import com.swing.sky.tiku.module.service.TiAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author swing
 */
@Service
@CurrentDataSource(DataSourceEnum.TIKU)
public class TiQuestionSearchServiceImpl implements TiQuestionSearchService {

    @Resource
    private TiQuestionSearchDAO questionSearchDAO;

    private CenterCourseService courseService;
    private TiAnswerService answerService;

    @Autowired
    public void setCourseService(CenterCourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setAnswerService(TiAnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public List<TiQuestionSearchDO> listAll() {
        List<TiQuestionSearchDO> list = questionSearchDAO.listAll();
        //完善课程名和答案数
        for (TiQuestionSearchDO question : list) {
            question.setCourseName(courseService.getById(question.getCourseId()).getCourseName());
            question.setAnswerNum(answerService.countAnswerByQuestionId(question.getId()));
        }
        return list;
    }
}
