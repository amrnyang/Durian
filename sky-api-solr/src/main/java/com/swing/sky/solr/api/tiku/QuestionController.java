package com.swing.sky.solr.api.tiku;

import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.solr.module.entity.QuestionQueryResult;
import com.swing.sky.solr.module.service.SolrQuestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 为题目模块提供搜素引擎的支持
 *
 * @author swing
 */
@Api
@RestController
@RequestMapping("solr/tiku/question")
public class QuestionController {
    private SolrQuestionService questionService;

    @Autowired
    public void setQuestionService(SolrQuestionService questionService) {
        this.questionService = questionService;
    }

    @Value("${sky-security.updateKey}")
    private String updateKey;

    /**
     * 更新引擎内的检索数据
     * (允许跨域请求）
     *
     * @param key 更新密钥
     */
    @CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}, maxAge = 3600, allowedHeaders = {}, exposedHeaders = {})
    @PostMapping("update")
    public SkyResponse updateQuestionData(String key) {
        if (key == null) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "未检测到有效的密钥");
        }
        if (!key.equalsIgnoreCase(updateKey)) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "无效的更新密钥，请重新输入");
        }
        questionService.updateQuestionSolrData();
        return SkyResponse.success("引擎题库数据更新成功！");
    }

    /**
     * 搜题
     * (允许跨域请求）
     *
     * @param key          密钥
     * @param courseId     课程id
     * @param condition    条件
     * @param questionType 题目类型
     * @param pageStart    页码
     * @param pageRows     页大小
     * @return 结果
     */
    @GetMapping("search")
    @CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}, maxAge = 3600, allowedHeaders = {}, exposedHeaders = {})
    public SkyResponse searchQuestion(String key, String condition, Long courseId, String questionType, Integer pageStart, Integer pageRows) {
        if (key == null) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "未检测到有效的密钥");
        }
        if (!key.equalsIgnoreCase(updateKey)) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "无效的查询密钥，请重新输入");
        }
        QuestionQueryResult queryResult = questionService.searchQuestion(condition, courseId, questionType, "ti_keywords", pageStart, pageRows);
        return SkyResponse.success(3)
                .put("queryTime", queryResult.getQueryTime())
                .put("numFound", queryResult.getNumFound())
                .put("questions", queryResult.getQuestions());
    }
}
