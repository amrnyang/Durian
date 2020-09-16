package com.swing.sky.es.framework.datasource.aspect;


import com.swing.sky.common.annotation.CurrentDataSource;
import com.swing.sky.es.framework.datasource.mysql.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源切换处理 将注解作用在DAO层或Service的方法上即可
 * Aspect:作用是把当前类标识为一个切面供容器读取
 * Pointcut：Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。方法签名必须是 public void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为 此表达式命名。因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
 * Around：环绕增强，相当于MethodInterceptor
 * AfterReturning：后置增强，相当于AfterReturningAdvice，方法正常退出时执行
 * Before：标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
 * AfterThrowing：异常抛出增强，相当于ThrowsAdvice
 * After: final增强，不管是抛出异常或者正常退出都会执行
 *
 * @author swing
 */
@Aspect
@Order(1)
@Component
public class CurrentDataSourceAspect {
    /**
     * 使用注解标识切点(方法级别或类级别）
     */
    @Pointcut("@annotation(com.swing.sky.common.annotation.CurrentDataSource)" + "|| @within(com.swing.sky.common.annotation.CurrentDataSource)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        CurrentDataSource dataSource = getDataSource(point);
        if (dataSource != null) {
            DynamicDataSourceContextHolder.setCurrentDataSource(dataSource.value().name());
        }
        try {
            return point.proceed();
        } finally {
            // 清空ThreadLocal中的数据源信息
            DynamicDataSourceContextHolder.clean();
        }
    }

    /**
     * 获取需要切换的数据源
     */
    public CurrentDataSource getDataSource(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();
        CurrentDataSource targetDataSource = targetClass.getAnnotation(CurrentDataSource.class);
        if (targetDataSource != null) {
            return targetDataSource;
        } else {
            Method method = signature.getMethod();
            return method.getAnnotation(CurrentDataSource.class);
        }
    }
}
