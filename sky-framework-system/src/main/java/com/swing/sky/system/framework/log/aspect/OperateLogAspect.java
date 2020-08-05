package com.swing.sky.system.framework.log.aspect;

import com.alibaba.fastjson.JSON;
import com.swing.sky.common.annotation.OperateLog;
import com.swing.sky.common.utils.IpUtils;
import com.swing.sky.common.utils.StringUtils;
import com.swing.sky.system.framework.security.utils.UserDetailsUtil;
import com.swing.sky.system.framework.task.AsyncTaskService;
import com.swing.sky.system.framework.task.factory.AsyncTaskFactory;
import com.swing.sky.system.framework.web.utils.ServletUtils;
import com.swing.sky.system.module.domain.SysLogOperateDO;
import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * 操作日志记录处理
 *
 * @author swing
 */
@Aspect
@Order(2)
@Component
public class OperateLogAspect {
    private static final Logger log = LoggerFactory.getLogger(OperateLogAspect.class);

    @Resource
    private AsyncTaskFactory asyncTaskFactory;

    @Resource
    private AsyncTaskService asyncTaskService;


    /**
     * 配置织入点,使用注解的方式
     */
    @Pointcut("@annotation(com.swing.sky.common.annotation.OperateLog)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行的日志
     *
     * @param joinPoint  切点
     * @param jsonResult 该方法操作完后返回的结果
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作后执行的日志
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    /**
     * 日志的执行
     *
     * @param joinPoint  日志加入点
     * @param e          错误类型
     * @param jsonResult json结果
     */
    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            // 获得Log注解
            OperateLog operateLog = getAnnotationLog(joinPoint);
            if (operateLog == null) {
                return;
            }

            //获取该上下文中的请求
            HttpServletRequest request = ServletUtils.getRequest();
            //获取IP地址
            String ip = IpUtils.getIpAddr(request);
            String url = request.getRequestURL().toString();
            //根据ip地址获取位置
            String location = IpUtils.getLocationByIp(ip);
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            // 获取客户端操作系统
            String os = userAgent.getOperatingSystem().getName();
            // 获取客户端浏览器
            String browser = userAgent.getBrowser().getName();

            // *========数据库日志=========*//
            SysLogOperateDO operateDO = new SysLogOperateDO();
            operateDO.setOperater(UserDetailsUtil.getUsername());
            operateDO.setModule(operateLog.module());
            operateDO.setBusinessType(operateLog.businessType());
            // 设置请求方式
            operateDO.setRequestMethod(request.getMethod());
            operateDO.setRequestUrl(url);
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operateDO.setJavaMethod(className + "." + methodName + "()");
            if (e == null) {
                operateDO.setSuccess(true);
                operateDO.setMessage("操作成功");
            } else {
                operateDO.setSuccess(false);
                operateDO.setMessage(e.getMessage());
            }
            // 设置该操作的json返回结果，去掉data
            operateDO.setResult(JSON.toJSONString(jsonResult));
            operateDO.setIp(ip);
            operateDO.setLocation(location);
            operateDO.setOs(os);
            operateDO.setBrowser(browser);
            operateDO.setCreateTime(new Date());


            // 处理设置在注解上的参数
            getControllerMethodDescription(joinPoint, operateLog, operateDO);

            // 保存数据库(异步线程执行日志记录操作）
            asyncTaskService.execute(asyncTaskFactory.recordOperateLog(operateDO));
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private OperateLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //获取切点上的方法
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(OperateLog.class);
        }
        return null;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log        日志
     * @param operateLog 操作日志
     * @throws Exception 异常
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, OperateLog log, SysLogOperateDO operateLog) throws Exception {
        // 设置业务描述（ordinal设置枚举对象在类中的索引）
        operateLog.setBusinessType(log.businessType());
        // 设置标题
        operateLog.setModule(log.module());
        // 设置操作人类别
        operateLog.setClientType("A");
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operateLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operateLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(JoinPoint joinPoint, SysLogOperateDO operateLog) throws Exception {
        String requestMethod = operateLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operateLog.setRequestParam(StringUtils.substring(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operateLog.setRequestParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }


    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (int i = 0; i < paramsArray.length; i++) {
                if (!isFilterObject(paramsArray[i])) {
                    Object jsonObj = JSON.toJSON(paramsArray[i]);
                    params += jsonObj.toString() + " ";
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    public boolean isFilterObject(final Object o) {
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }
}
