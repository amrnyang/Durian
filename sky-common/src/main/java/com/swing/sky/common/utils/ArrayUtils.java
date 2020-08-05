package com.swing.sky.common.utils;

import cn.hutool.core.util.ArrayUtil;

/**
 * 数组操作工具类
 *
 * @author swing
 */
public class ArrayUtils extends ArrayUtil {
    /**
     * 判断一个数据是否是另一个数组的子集
     *
     * @param a 父数组
     * @param b 子数组
     * @return 是否包含
     */
    public static boolean contains(Long[] a, Long[] b) {
        for (Long l : b) {
            if (!contains(a, l)) {
                return false;
            }
        }
        return true;
    }
}
