package com.swing.sky.common.annotation;


import com.swing.sky.common.constant.BusinessTypeConstants;
import com.swing.sky.common.constant.ModuleConstants;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 *
 * @author swing
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {
    /**
     * 模块名
     */
    String module() default ModuleConstants.OPERATE_LOG;

    /**
     * 业务类型
     */
    String businessType() default BusinessTypeConstants.OTHER;

    /**
     * 操作人类别（用户端;管理端）
     */
    String operatorType() default "其他";

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
