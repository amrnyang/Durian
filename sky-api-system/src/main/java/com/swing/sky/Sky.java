package com.swing.sky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author swing
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Sky {
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Sky.class, args);
        System.out.println("启动成功....");
    }
}
