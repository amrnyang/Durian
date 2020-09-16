package com.swing.sky.es.framework.datasource.elasticsearch.service.impl.tiku;

import com.swing.sky.es.framework.datasource.constants.EsConstants;
import com.swing.sky.es.framework.datasource.elasticsearch.EsClient;
import com.swing.sky.es.framework.datasource.elasticsearch.service.SearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 题库搜索的实现接口
 *
 * @author swing
 */
@Service
public class TikuQuestionSearchServiceImpl implements SearchService {

    @Resource
    private EsClient esClient;


    @Override
    public SearchResponse search(QueryBuilder queryBuilder) throws IOException {
        RestHighLevelClient restHighLevelClient = esClient.getRestClient();
        SearchRequest searchRequest = new SearchRequest(EsConstants.TIKU_QUESTION_INDEX);
        searchRequest.types(EsConstants.TIKU_QUESTION_TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.query(queryBuilder);

        searchRequest.source(searchSourceBuilder);

        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }

    @Override
    public SearchResponse idQuery(Long id) throws IOException {
        return null;
    }

    @Override
    public SearchResponse idsQuery() throws IOException {
        return null;
    }

    @Override
    public SearchResponse boolMustQuery(SearchSourceBuilder searchSourceBuilder) throws IOException {
        RestHighLevelClient restHighLevelClient = esClient.getRestClient();
        SearchRequest searchRequest = new SearchRequest(EsConstants.TIKU_QUESTION_INDEX);
        searchRequest.types(EsConstants.TIKU_QUESTION_TYPE);
        searchRequest.source(searchSourceBuilder);
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void termQuery() throws IOException {

    }

    @Override
    public void termsQuery() throws IOException {

    }

    @Override
    public void matchQuery() throws IOException {

    }

    @Override
    public void matchBooleanQuery() throws IOException {

    }

    @Override
    public void multiMatchQuery() throws IOException {

    }

    @Override
    public void prefixQuery() throws IOException {

    }

    @Override
    public void fuzzyQuery() throws IOException {

    }

    @Override
    public void wildcardQuery() throws IOException {

    }

    @Override
    public void rangeQuery() throws IOException {

    }

    @Override
    public void regexpQuery() throws IOException {

    }

    @Override
    public void scrollQuery() throws IOException {

    }

    @Override
    public void deleteByQuery() throws IOException {

    }

    @Override
    public void highlightQuery() throws IOException {

    }

    @Override
    public void aggregateQuery() throws IOException {

    }
}
