package com.swing.sky.tiku.api;

import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.tiku.module.domain.TiAnswerDO;
import com.swing.sky.tiku.module.domain.TiQuestionDO;
import com.swing.sky.tiku.module.service.TiAnswerService;
import com.swing.sky.tiku.module.service.TiQuestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 与题目相关的接口
 *
 * @author swing
 */
@Api
@RestController
@RequestMapping("tiku/question")
public class QuestionController {
    /**
     * 接口访问密钥
     */
    @Value("${sky-security.accessKey}")
    private String accessKey;

    private TiQuestionService questionService;
    private TiAnswerService answerService;

    @Autowired
    public void setQuestionService(TiQuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setAnswerService(TiAnswerService answerService) {
        this.answerService = answerService;
    }

    /**
     * 根据question_id获取问题的详细信息和答案列表
     */
    @GetMapping
    public SkyResponse getQuestionDetail(Long id, String key) {
        if (key == null) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "未检测到有效的密钥");
        }
        if (id == null) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "请输入有效的题号");
        }
        if (!key.equalsIgnoreCase(accessKey)) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "无效的访问密钥，请重新输入");
        }
        TiQuestionDO question = questionService.getById(id);
        if(question==null){
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "未检测到有效的题目");
        }
        //获取改题目的答案列表(已审核的答案）
        TiAnswerDO answer = new TiAnswerDO();
        answer.setAuditStatus("B");
        answer.setQuestionId(id);
        List<TiAnswerDO> answerList = answerService.listByCondition(answer, null, null);
        return SkyResponse.success(2)
                .put("question", question)
                .put("answerList", answerList);
    }
}
