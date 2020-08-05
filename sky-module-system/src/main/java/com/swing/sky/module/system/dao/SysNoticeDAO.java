package com.swing.sky.module.system.dao;

import com.swing.sky.common.basic.BasicDAO;
import com.swing.sky.module.system.domain.SysNoticeDO;

/**
 * @author swing
 * 筛选字段
 * noticeTitle:公告标题
 * noticeType:公告类型
 * noticeContent:公告内容
 * use:是否使用
 * beginTime-endTime:记录创建时间
 */
public interface SysNoticeDAO extends BasicDAO<SysNoticeDO> {

}