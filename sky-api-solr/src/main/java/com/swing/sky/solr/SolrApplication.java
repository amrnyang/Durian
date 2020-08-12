package com.swing.sky.solr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author swing
 */
@SpringBootApplication
public class SolrApplication {
    public static void main(String[] args) {
        SpringApplication.run(SolrApplication.class, args);
        System.out.println("Solr模块启动成功....");
    }
}
