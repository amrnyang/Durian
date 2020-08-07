package com.swing.sky.tiku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author swing
 */
@SpringBootApplication
public class TiKuApplication {
    public static void main(String[] args) {
        SpringApplication.run(TiKuApplication.class, args);
        System.out.println("前台程序启动成功....");
    }
}