package com.swing.sky.center.framework.datasource.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author swing
 */
@MapperScan(basePackages = {
        "com.swing.sky.center.module.dao"
})
@Configuration
public class MybatisConfig {
}
