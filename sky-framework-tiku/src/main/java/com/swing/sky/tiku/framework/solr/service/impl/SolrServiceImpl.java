package com.swing.sky.tiku.framework.solr.service.impl;

import com.swing.sky.tiku.framework.solr.dao.QuestionDAO;
import com.swing.sky.tiku.framework.solr.entity.Question;
import com.swing.sky.tiku.framework.solr.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author swing
 */
@Service
public class SolrServiceImpl implements SolrService {
    @Resource
    private QuestionDAO questionDAO;

    @Resource
    private SolrClient solrClient;

    @Override
    public void updateQuestionSolrData() {
        //获取当前所有审核通过的答案
        List<Question> questions = questionDAO.listAllQuestions();

        //先清空引擎中的数据
        cleanSolr();
        SolrInputDocument document;
        for (Question question : questions) {
            document = new SolrInputDocument();
            document.addField("id", question.getTiQuestionId());
            document.addField("ti_question_course_id", question.getTiQuestionCourseId());
            document.addField("ti_course_course_name", question.getTiCourseCourseName());
            document.addField("ti_question_content", question.getTiQuestionContent());
            document.addField("ti_question_question_type", question.getTiQuestionQuestionType());
            document.addField("ti_question_review_point", question.getTiQuestionReviewPoint());

            try {
                solrClient.add(document);
                solrClient.commit();
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cleanSolr() {
        try {
            solrClient.deleteByQuery("*:*");
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Question> searchQuestion(String condition, String df, Integer pageStart, Integer pageRows) {
        // 创建查询对象
        SolrQuery query = new SolrQuery();
        query.setQuery(condition);
        query.set("df", df);
        query.setStart(pageStart);
        query.setRows(pageRows);
        // 设置高亮显示
        query.setHighlight(true);
        query.addHighlightField("ti_question_content");
        query.setHighlightSimplePre("<span style='color:red;'>");
        query.setHighlightSimplePost("</span>");

        List<Question> questions = new ArrayList<>();
        try {
            // 执行查询操作
            QueryResponse response = solrClient.query(query);

            // 获取查询结果集
            SolrDocumentList results = response.getResults();

            // 获取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

            // 遍历结果集
            for (SolrDocument result : results) {
                Question question = new Question();
                Object id = result.get("id");
                question.setTiQuestionId(Long.parseLong((String) id));
                String questionContent = "";
                List<String> strings = highlighting.get(id).get("ti_question_content");
                if (strings != null && strings.size() > 0) {
                    questionContent = strings.get(0);
                } else {
                    questionContent = (String) result.get("ti_question_content");
                }
                question.setTiQuestionContent(questionContent);
                question.setTiQuestionCourseId((Long) result.get("ti_question_course_id"));
                question.setTiCourseCourseName((String) result.get("ti_course_course_name"));
                question.setTiQuestionQuestionType((String) result.get("ti_question_question_type"));
                question.setTiQuestionReviewPoint((String) result.get("ti_question_review_point"));
                questions.add(question);
            }
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}



































