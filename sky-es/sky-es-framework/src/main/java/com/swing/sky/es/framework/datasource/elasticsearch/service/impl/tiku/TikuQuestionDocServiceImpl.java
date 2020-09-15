package com.swing.sky.es.framework.datasource.elasticsearch.service.impl.tiku;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swing.sky.es.framework.datasource.constants.EsConstants;
import com.swing.sky.es.framework.datasource.elasticsearch.EsClient;
import com.swing.sky.es.framework.datasource.elasticsearch.service.DocService;
import com.swing.sky.es.module.domain.TiQuestionSearchDO;
import com.swing.sky.es.module.service.TiQuestionSearchService;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 题库模块的文档接口实现
 *
 * @author swing
 */
@Service
public class TikuQuestionDocServiceImpl implements DocService {
    private TiQuestionSearchService questionSearchService;

    @Autowired
    public void setQuestionSearchService(TiQuestionSearchService questionSearchService) {
        this.questionSearchService = questionSearchService;
    }

    @Resource
    private EsClient esClient;

    @Override
    public void updateDoc() throws IOException {
        //获取es客户端
        RestHighLevelClient restClient = esClient.getRestClient();
        ObjectMapper objectMapper = new ObjectMapper();
        //获取当前题库的题目数据
        List<TiQuestionSearchDO> list = questionSearchService.listAll();
        int count = 0;
        for (TiQuestionSearchDO question : list) {
            count++;
            String string = objectMapper.writeValueAsString(question);
            IndexRequest indexRequest = new IndexRequest(EsConstants.TIKU_QUESTION_INDEX, EsConstants.TIKU_QUESTION_TYPE, question.getId().toString());
            indexRequest.source(string, XContentType.JSON);
            try {
                IndexResponse indexResponse = restClient.index(indexRequest, RequestOptions.DEFAULT);
            } catch (ElasticsearchStatusException e) {
                System.out.println(question.toString());
            }
            if (count % 100 == 0) {
                System.out.println("当前同步数据---" + count);
            }
        }
    }
}
