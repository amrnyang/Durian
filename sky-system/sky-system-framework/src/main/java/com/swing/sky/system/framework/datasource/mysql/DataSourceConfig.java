package com.swing.sky.system.framework.datasource.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.swing.sky.common.enums.DataSourceEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 * 由于是多数据源配置，所以需要手动读取application.yml中的参数，如果是单数据源，springBoot会自动完成数据源的配置
 *
 * @author swing
 */
@Configuration
public class DataSourceConfig {
    @Value("${spring.datasource.druid.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.druid.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.druid.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.druid.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.maxEvictableIdleTimeMillis}")
    private int maxEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.druid.testOnReturn}")
    private boolean testOnReturn;

    /**
     * 初始化DruidDateSource 的基本配置（不不包括数据源位置）
     *
     * @param druidDataSource 数据源
     * @return 数据源
     */
    @ConfigurationProperties("spring.datasource.druid")
    public DruidDataSource initDataSource(DruidDataSource druidDataSource) {
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        return druidDataSource;
    }

    /**
     * 后台管理模块数据源
     *
     * @return 后台管理模块数据源
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.system")
    public DataSource systemDataSource() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return initDataSource(dataSource);
    }

    /**
     * 信息中心数据源
     *
     * @return 信息中心数据源
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.center")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.center", name = "enabled", havingValue = "true")
    public DataSource centerDataSource() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return initDataSource(dataSource);
    }

    /**
     * 题库数据源
     *
     * @return 信息中心数据源
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.tiku")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.tiku", name = "enabled", havingValue = "true")
    public DataSource tikuDataSource() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return initDataSource(dataSource);
    }

    /**
     * 动态数据源
     *
     * @param systemDataSource 后台管理模块数据源
     * @param centerDataSource 信息中心数据源
     * @return 动态数据源
     */
    @Bean
    public DynamicDataSource dynamicDataSource(DataSource systemDataSource, DataSource centerDataSource, DataSource tikuDataSource) {
        //数据源集合(指定集合的大小）
        Map<Object, Object> dataSources = new HashMap<>(3);
        //将所有的数据源放入集合
        dataSources.put(DataSourceEnum.SYSTEM.name(), systemDataSource);
        dataSources.put(DataSourceEnum.CENTER.name(), centerDataSource);
        dataSources.put(DataSourceEnum.TIKU.name(), tikuDataSource);
        //将后台管理数据源设置为动态数据源的默认数据源
        return new DynamicDataSource(systemDataSource, dataSources);
    }

}
