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
@ComponentScan(value = {"com.swing.sky.tiku.module", "com.swing.sky.system.module", "com.swing.sky.system.framework","com.swing.sky.system.api"})
public class Sky {
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Sky.class, args);
        System.out.println("启动成功....");
    }
}
