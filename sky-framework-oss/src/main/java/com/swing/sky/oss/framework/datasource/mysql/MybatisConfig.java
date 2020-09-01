package com.swing.sky.oss.framework.datasource.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author swing
 */
@MapperScan(basePackages = {
        "com.swing.sky.oss.module.dao"
})
@Configuration
public class MybatisConfig {
}
