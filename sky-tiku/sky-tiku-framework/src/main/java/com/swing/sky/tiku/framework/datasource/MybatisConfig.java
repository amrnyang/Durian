package com.swing.sky.tiku.framework.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author swing
 */
@MapperScan(basePackages = {
        "com.swing.sky.common.basic",
        "com.swing.sky.tiku.module.dao",
        "com.swing.sky.system.module.dao"
})
@Configuration
public class MybatisConfig {
}
