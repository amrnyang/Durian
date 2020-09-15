package com.swing.sky.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 *
 * @author swing
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(value = {"com.swing.sky.system", "com.swing.sky.center.module","com.swing.sky.tiku.module","com.swing.sky.es.inner"})
public class Sky {
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Sky.class, args);
        System.out.println("后台管理模块启动成功....");
    }
}
