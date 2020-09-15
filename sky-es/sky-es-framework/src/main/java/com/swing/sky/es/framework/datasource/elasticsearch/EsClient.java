package com.swing.sky.es.framework.datasource.elasticsearch;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author swing
 */
@Configuration
public class EsClient {
    /**
     * 地址
     */
    @Value("${elasticsearch.hostName}")
    private String hostName;

    /**
     * 版本
     */
    @Value("${elasticsearch.port}")
    private Integer port;

    public RestHighLevelClient getRestClient() {
        HttpHost httpHost = new HttpHost(hostName, port);
        RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
        return new RestHighLevelClient(restClientBuilder);
    }
}
