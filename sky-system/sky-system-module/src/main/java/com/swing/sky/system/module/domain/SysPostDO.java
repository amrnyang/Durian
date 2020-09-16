package com.swing.sky.system.module.domain;

import com.swing.sky.system.module.annotation.Excel;
import com.swing.sky.common.basic.BasicDO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 岗位
 * sys_post
 *
 * @author swing
 */
public class SysPostDO extends BasicDO implements Serializable {

    /**
     * 岗位名称
     */
    @Excel(name = "岗位名称")
    private String postName;

    public SysPostDO(@NotNull Boolean use, Integer orderNum, String createBy, Date createTime, String updateBy, Date updateTime, String remark, String postName) {
        super(use, orderNum, createBy, createTime, updateBy, updateTime, remark);
        this.postName = postName;
    }

    public SysPostDO() {
    }

    private static final long serialVersionUID = 1L;

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    @Override
    public String toString() {
        return "SysPostDO{" +
                "postName='" + postName + '\'' +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysPostDO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SysPostDO sysPostDO = (SysPostDO) o;
        return Objects.equals(postName, sysPostDO.postName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), postName);
    }
}