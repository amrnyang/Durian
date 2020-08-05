package com.swing.sky.common.utils;

import java.util.UUID;

/**
 * uuid工具类
 *
 * @author swing
 */
public class UUIDUtils {
    /**
     * 产生全球唯一的字符串
     *
     * @return 唯一字符串
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}