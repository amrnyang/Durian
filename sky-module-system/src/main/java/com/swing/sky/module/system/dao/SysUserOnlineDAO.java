package com.swing.sky.module.system.dao;

import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.module.system.domain.SysUserOnlineDO;
import org.apache.ibatis.annotations.Param;

/**
 * 在线用户信息
 *
 * @author swing
 * 筛选字段：
 * username:在线用户名
 * clientType:客户端类型
 * location:位置
 * ip:ip地址
 * os:操作系统
 * browser:浏览器
 * online:是否在线
 * beginTime-endTime:记录创建时间
 */
public interface SysUserOnlineDAO extends BasicDAO<SysUserOnlineDO> {
    //--------------------------------唯一字段校验方法-----------------------------------------

    /**
     * 校验sessionId的唯一性
     *
     * @param sessionId session id
     * @param id        记录id
     * @return 数量
     */
    int countBySessionId(@Param("sessionId") String sessionId, @Param("id") Long id);
}