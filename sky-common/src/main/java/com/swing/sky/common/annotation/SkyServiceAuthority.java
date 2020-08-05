package com.swing.sky.common.annotation;

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
}
