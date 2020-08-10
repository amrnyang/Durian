package com.swing.sky.system.framework.datasource.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author swing
 */
@MapperScan(basePackages = {
        "com.swing.sky.common.basic",
        "com.swing.sky.system.module.dao",
        "com.swing.sky.tiku.module.dao"
})
@Configuration
public class MybatisConfig {
}
