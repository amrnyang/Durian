package com.swing.sky.common.annotation;


import com.swing.sky.common.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * 指定当前数据源注解
 *
 * @author swing
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CurrentDataSource {
    /**
     * 切换数据源名称
     */
    public DataSourceEnum value() default DataSourceEnum.MASTER;
}
