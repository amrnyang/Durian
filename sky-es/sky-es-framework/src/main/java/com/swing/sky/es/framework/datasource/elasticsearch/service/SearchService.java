package com.swing.sky.es.framework.datasource.elasticsearch.service;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * ES搜索文档信息的接口
 *
 * @author swing
 */
public interface SearchService {

    /**
     * 查询
     *
     * @param queryBuilder 查询条件
     * @throws IOException 异常
     * @return 结果
     */
    SearchResponse search(QueryBuilder queryBuilder) throws IOException;

    /**
     * id
     *
     * @throws IOException 异常
     * @param id 信息id
     */
    SearchResponse idQuery(Long id) throws IOException;

    /**
     * ids
     *
     * @throws IOException 异常
     */
    SearchResponse idsQuery() throws IOException;

    /**
     * 复合查询
     *
     * @throws IOException 异常
     * @return 结果
     */
    SearchResponse boolMustQuery(SearchSourceBuilder searchSourceBuilder) throws IOException;

    /**
     * term查询,关键字查询前不分词，因此 ‘联想天佑专卖店’ 匹配不到 ‘联想天佑专卖店’
     *
     * @throws IOException 异常
     */
    void termQuery() throws IOException;

    /**
     * terms查询,与terms同义，不同之处在于可以指定多个匹配条件
     *
     * @throws IOException 异常
     */
    void termsQuery() throws IOException;

    /**
     * match查询
     *
     * @throws IOException 异常
     */
    void matchQuery() throws IOException;

    /**
     * match查询(and or)
     *
     * @throws IOException 异常
     */
    void matchBooleanQuery() throws IOException;

    /**
     * multi_match 指定再多个field中查询
     *
     * @throws IOException 异常
     */
    void multiMatchQuery() throws IOException;

    /**
     * prefix前置查询
     *
     * @throws IOException 异常
     */
    void prefixQuery() throws IOException;

    /**
     * fuzzy 查询 模糊查询（可以有错别字）
     *
     * @throws IOException 异常
     */
    void fuzzyQuery() throws IOException;

    /**
     * wildcard 查询,类似like
     *
     * @throws IOException 异常
     */
    void wildcardQuery() throws IOException;

    /**
     * range 范围查询 (gte包含等于，gt不包含)
     *
     * @throws IOException 异常
     */
    void rangeQuery() throws IOException;

    /**
     * regexp 正则表达式查询 （prefix,fuzzy,wildcard ,regexp 效率略低）
     *
     * @throws IOException 异常
     */
    void regexpQuery() throws IOException;

    /**
     * scroll
     *
     * @throws IOException 异常
     */
    void scrollQuery() throws IOException;

    /**
     * 查询指定的文档，然后删除
     *
     * @throws IOException 异常
     */
    void deleteByQuery() throws IOException;

    /**
     * 高亮查询
     *
     * @throws IOException 异常
     */
    void highlightQuery() throws IOException;

    /**
     * 聚合查询
     *
     * @throws IOException 异常
     */
    void aggregateQuery() throws IOException;

}
