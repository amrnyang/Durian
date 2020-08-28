package com.swing.sky.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author swing
 */
@SpringBootApplication
public class OSSApplication {
    public static void main(String[] args) {
        SpringApplication.run(OSSApplication.class, args);
        System.out.println("单点登录程序启动成功....");
    }
}
