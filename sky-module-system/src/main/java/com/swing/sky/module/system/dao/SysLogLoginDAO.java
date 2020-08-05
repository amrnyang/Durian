package com.swing.sky.module.system.dao;

import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.module.system.domain.SysLogLoginDO;

/**
 * @author swing
 * 筛选字段：
 * username：登录用户名
 * clientType:客户端类型
 * location:位置
 * ip:ip地址
 * os:操作系统
 * browser:浏览器
 * success:是否登录成功
 * beginTime-endTime:记录创建时间
 */
public interface SysLogLoginDAO extends BasicDAO<SysLogLoginDO> {
}