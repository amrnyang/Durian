package com.swing.sky.es.api.tiku;

import com.swing.sky.common.utils.StringUtils;
import com.swing.sky.common.web.SkyResponse;
import com.swing.sky.es.api.dto.response.QuestionResponse;
import com.swing.sky.es.framework.datasource.elasticsearch.service.DocService;
import com.swing.sky.es.framework.datasource.elasticsearch.service.impl.tiku.TikuQuestionDocServiceImpl;
import com.swing.sky.es.framework.datasource.elasticsearch.service.impl.tiku.TikuQuestionSearchServiceImpl;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author swing
 */
@RestController
@RequestMapping("es/search/tiku/question")
public class QuestionEsController {

    @Resource
    private TikuQuestionSearchServiceImpl tikuQuestionSearchServiceImpl;

    @Resource
    private TikuQuestionDocServiceImpl tikuQuestionDocServiceImpl;

    /**
     * 访问密钥
     */
    @Value("${sky-security.accessKey}")
    private String accessKey;


    @PostMapping("/update-doc")
    @CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}, maxAge = 3600, allowedHeaders = {}, exposedHeaders = {})
    public SkyResponse updateDoc(String key) throws IOException {
        if (key == null) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "未检测到有效的密钥");
        }
        if (!key.equalsIgnoreCase(accessKey)) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "无效的更新密钥，请重新输入");
        }
        tikuQuestionDocServiceImpl.updateDoc();
        return SkyResponse.success("引擎题库数据更新成功！");
    }


    @GetMapping("/must")
    @CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}, maxAge = 3600, allowedHeaders = {}, exposedHeaders = {})
    public SkyResponse must(String key, Integer pageNum, Integer pageSize, String content, String courseId, String questionType, String creatorName) throws IOException {
        if (key == null) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "未检测到有效的密钥");
        }
        if (!key.equalsIgnoreCase(accessKey)) {
            return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "无效的更新密钥，请重新输入");
        }
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if (pageNum == null) {
            searchSourceBuilder.from(0);
        } else {
            searchSourceBuilder.from(pageNum);
        }
        if (pageSize == null) {
            searchSourceBuilder.size(10);
        } else {
            searchSourceBuilder.size(pageSize);
        }
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(content)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("content", content));
        }
        if (!StringUtils.isEmpty(courseId)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("courseId", courseId));
        }
        if (!StringUtils.isEmpty(questionType)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("questionType", questionType));
        }
        if (!StringUtils.isEmpty(creatorName)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("creatorName", creatorName));
        }
        searchSourceBuilder.query(boolQueryBuilder);
        //高亮设置
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder
                .field("content", 200)
                .preTags("<strong style=\"color: rgb(253, 174, 100);\">")
                .postTags("</strong>");
        searchSourceBuilder.highlighter(highlightBuilder);
        SearchResponse searchResponse = tikuQuestionSearchServiceImpl.boolMustQuery(searchSourceBuilder);
        List<QuestionResponse> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            QuestionResponse questionResponse = new QuestionResponse();
            questionResponse.setId(Long.parseLong(hit.getId()));
            Map<String, Object> map = hit.getSourceAsMap();
            if (hit.getHighlightFields().get("content") != null) {
                questionResponse.setContent(hit.getHighlightFields().get("content").fragments()[0].string());
            } else {
                questionResponse.setContent(map.get("content").toString());
            }
            questionResponse.setCourseName(map.get("courseName").toString());
            questionResponse.setReviewPoint(map.get("reviewPoint").toString());
            questionResponse.setCreatorName(map.get("creatorName").toString());
            questionResponse.setQuestionType(map.get("questionType").toString());
            questionResponse.setCreatorId(Long.parseLong(map.get("creatorId").toString()));
            questionResponse.setCourseId(Long.parseLong(map.get("courseId").toString()));
            questionResponse.setAnswerNum(Integer.parseInt(map.get("answerNum").toString()));
            questionResponse.setCreateTime(new Date(Long.parseLong(map.get("createTime").toString())));
            questionResponse.setUpdateTime(new Date(Long.parseLong(map.get("updateTime").toString())));
            list.add(questionResponse);
        }
        return SkyResponse.success("搜索成功", 2)
                .put("total", searchResponse.getHits().getTotalHits())
                .put("list", list);
    }

}
