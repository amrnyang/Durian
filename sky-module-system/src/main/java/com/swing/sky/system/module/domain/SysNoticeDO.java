package com.swing.sky.system.module.domain;

import com.swing.sky.common.basic.BasicDO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * sys_notice
 * 公告
 *
 * @author swing
 */
public class SysNoticeDO extends BasicDO implements Serializable {

    /**
     * 公告标题
     */
    @NotNull(message = "公告标题不为null")
    @Size(min = 1, max = 100, message = "公告标题长度范围在1~100")
    private String noticeTitle;

    /**
     * 公告类型（A通知 B公告 C 通告）
     */
    @NotNull(message = "公告类型不为null")
    @Size(min = 1, max = 1, message = "公告类型为一个字符")
    private String noticeType;

    /**
     * 公告内容
     */
    @NotNull(message = "公告内容不为null")
    private String noticeContent;

    public SysNoticeDO(@NotNull Boolean use, Integer orderNum, String createBy, Date createTime, String updateBy, Date updateTime, String remark,  @NotNull(message = "公告标题不为null") @Size(min = 1, max = 100, message = "公告标题长度范围在1~100") String noticeTitle, @NotNull(message = "公告类型不为null") @Size(min = 1, max = 1, message = "公告类型为一个字符") String noticeType, @NotNull(message = "公告内容不为null") @Size(min = 1, max = 3000, message = "公告内容长度范围在1~3000") String noticeContent) {
        super(use, orderNum, createBy, createTime, updateBy, updateTime, remark);
        this.noticeTitle = noticeTitle;
        this.noticeType = noticeType;
        this.noticeContent = noticeContent;
    }

    public SysNoticeDO() {
    }


    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysNoticeDO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SysNoticeDO that = (SysNoticeDO) o;
        return Objects.equals(noticeTitle, that.noticeTitle) &&
                Objects.equals(noticeType, that.noticeType) &&
                Objects.equals(noticeContent, that.noticeContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), noticeTitle, noticeType, noticeContent);
    }

    @Override
    public String toString() {
        return "SysNoticeDO{" +
                "noticeTitle='" + noticeTitle + '\'' +
                ", noticeType='" + noticeType + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", id=" + id +
                ", use=" + use +
                ", orderNum=" + orderNum +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}