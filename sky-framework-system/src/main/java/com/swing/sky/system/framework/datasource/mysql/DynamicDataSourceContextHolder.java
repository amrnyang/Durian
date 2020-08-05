package com.swing.sky.system.framework.datasource.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用ThreadLocal来切换数据源
 *
 * @author swing
 */
public class DynamicDataSourceContextHolder {
    public static final Logger log = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    /**
     * ThreadLocal是Context的代理对象，维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置当前数据源的变量
     *
     * @param dataSourceName 数据源名
     */
    public static void setCurrentDataSource(String dataSourceName) {
        log.info("当前数据源为：{}", dataSourceName);
        CONTEXT_HOLDER.set(dataSourceName);
    }

    /**
     * 获得当前数据源的变量
     *
     * @return 数据源名
     */
    public static String getCurrentDataSource() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清空ThreadLocal中的值
     */
    public static void clean() {
        CONTEXT_HOLDER.remove();
    }
}
