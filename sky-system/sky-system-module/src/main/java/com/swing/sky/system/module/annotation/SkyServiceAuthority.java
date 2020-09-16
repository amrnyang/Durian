package com.swing.sky.system.module.annotation;

import java.lang.annotation.*;

/**
 * Service层权限范围预处理
 *
 * @author swing
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SkyServiceAuthority {
    /**
     * 模块名
     */
    String moduleName() default "";

    /**
     * 是否鉴权，默认为true，为false标识只添加公共字段，不鉴权
     */
    boolean isAuth() default true;
}
