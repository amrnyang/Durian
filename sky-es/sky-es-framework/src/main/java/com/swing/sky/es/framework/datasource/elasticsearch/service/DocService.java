package com.swing.sky.es.framework.datasource.elasticsearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 操作ES文档信息的接口
 *
 * @author swing
 */
public interface DocService {
    /**
     * 更新文档信息
     *
     * @throws IOException
     */
    public void updateDoc() throws IOException;
}
