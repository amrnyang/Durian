package com.swing.sky.system.framework.task.factory;

import com.swing.sky.common.utils.IpUtils;
import com.swing.sky.common.utils.ServletUtils;
import com.swing.sky.system.module.domain.SysLogLoginDO;
import com.swing.sky.system.module.domain.SysLogOperateDO;
import com.swing.sky.system.module.service.SysLogLoginService;
import com.swing.sky.system.module.service.SysLogOperateService;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.TimerTask;

/**
 * 生产异步任务工厂（产生需要异步执行的TimerTask）
 *
 * @author swing
 */
@Component
public class AsyncTaskFactory {
    private SysLogLoginService logLoginService;

    private SysLogOperateService logOperateService;

    @Autowired
    public void setLogLoginService(SysLogLoginService logLoginService) {
        this.logLoginService = logLoginService;
    }

    @Autowired
    public void setLogOperateService(SysLogOperateService logOperateService) {
        this.logOperateService = logOperateService;
    }

    /**
     * 记录登陆信息日志
     *
     * @param success 登录成功与否
     * @param message 消息
     * @param args    列表
     * @return 任务task
     */
    public TimerTask recordLoginLog(Boolean success, String message, final Object... args) {
        //获取该上下文中的请求
        HttpServletRequest request = ServletUtils.getRequest();
        //获取登录用户名
        String username = request.getParameter("username");
        //获取IP地址
        String ip = IpUtils.getIpAddr(request);
        //根据ip地址获取位置
        String location = IpUtils.getLocationByIp(ip);
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        return new TimerTask() {
            @Override
            public void run() {
                // 封装对象
                SysLogLoginDO logLoginDO = new SysLogLoginDO();
                logLoginDO.setUsername(username);
                logLoginDO.setIp(ip);
                logLoginDO.setLocation(location);
                logLoginDO.setClientType("A");
                logLoginDO.setCreateTime(new Date());
                logLoginDO.setBrowser(browser);
                logLoginDO.setOs(os);
                logLoginDO.setMessage(message);
                logLoginDO.setSuccess(success);
                // 插入数据
                logLoginService.insert(logLoginDO);
            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param logOperateDO 操作日志信息
     * @return 任务task
     */
    public TimerTask recordOperateLog(final SysLogOperateDO logOperateDO) {
        return new TimerTask() {
            @Override
            public void run() {
                logOperateService.insert(logOperateDO);
            }
        };
    }
}
