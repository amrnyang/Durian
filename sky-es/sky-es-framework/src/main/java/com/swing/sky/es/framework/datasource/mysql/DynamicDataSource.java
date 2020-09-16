package com.swing.sky.es.framework.datasource.mysql;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源(超类为DataSource)
 *
 * @author swing
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * @param defaultTargetDataSource 默认数据源
     * @param targetDataSources       数据源集合
     */
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    /**
     * 决定当前使用的数据源，使用ThreadLocal存储当前线程使用的数据源名
     *
     * @return 数据源在targetDataSources中的 key 值
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getCurrentDataSource();
    }
}