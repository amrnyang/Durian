package com.swing.sky.solr.framework.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author swing
 */
@MapperScan(basePackages = {
        "com.swing.sky.solr.module.dao"
})
@Configuration
public class MybatisConfig {
}
