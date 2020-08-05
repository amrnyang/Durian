package com.swing.sky.framework.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 确保在销毁此Bean前关闭后台守护线程池
 *
 * @author swing
 */
@Component
public class ShutdownManager {
    private static final Logger logger = LoggerFactory.getLogger("sys-user");

    @Resource
    private AsyncTaskService asyncTaskService;

    @PreDestroy
    public void destroy() {
        shutdownAsyncManager();
    }

    /**
     * 停止异步执行任务
     */
    private void shutdownAsyncManager() {
        try {
            logger.info("====关闭后台任务任务线程池====");
            asyncTaskService.shutdown();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
