package com.swing.sky.common.utils;

import cn.hutool.db.sql.SqlUtil;

/**
 * @author swing
 */
public class SkySqlUtil extends SqlUtil {
    /**
     * 仅支持字母、数字、下划线、空格、逗号（支持多个字段排序）
     */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,]+";

    /**
     * 检查字符，防止注入绕过
     */
    public static String escapeOrderBySql(String orderProperty, String isAsc) {
        String value = getOrderBy(orderProperty, isAsc);
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value)) {
            return StringUtils.EMPTY;
        }
        return value;
    }

    /**
     * 验证 order by 语法是否符合规范
     */
    public static boolean isValidOrderBySql(String value) {
        return value.matches(SQL_PATTERN);
    }

    /**
     * 通过排序的属性和排序模式获得sql语句中的 order by 语句
     *
     * @param orderProperty 排序属性
     * @param isAsc         排序模式
     * @return orderBy语句
     */
    public static String getOrderBy(String orderProperty, String isAsc) {
        if (StringUtils.isEmpty(orderProperty)) {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderProperty) + " " + isAsc;
    }
}
