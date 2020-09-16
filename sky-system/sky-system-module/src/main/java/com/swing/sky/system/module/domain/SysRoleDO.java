package com.swing.sky.system.module.domain;

import com.swing.sky.system.module.annotation.Excel;
import com.swing.sky.common.basic.BasicDO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * sys_role
 * 角色
 *
 * @author swing
 */
public class SysRoleDO extends BasicDO implements Serializable {

    /**
     * 角色名称
     */
    @Excel(name = "角色名称")
    @NotNull(message = "角色名称不为null")
    @Size(min = 1, max = 100, message = "角色名称长度范围在1~100")
    private String roleName;

    /**
     * 是否删除（1 是 0：否）
     */
    @Excel(name = "是否删除", readConverterExp = "true=是,false=否")
    private Boolean deleted;

    public SysRoleDO(@NotNull Boolean use, Integer orderNum, String createBy, Date createTime, String updateBy, Date updateTime, String remark, @NotNull(message = "角色名称不为null") @Size(min = 1, max = 100, message = "角色名称长度范围在1~100") String roleName, Boolean deleted) {
        super(use, orderNum, createBy, createTime, updateBy, updateTime, remark);
        this.roleName = roleName;
        this.deleted = deleted;
    }

    public SysRoleDO() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRoleDO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SysRoleDO sysRoleDO = (SysRoleDO) o;
        return Objects.equals(roleName, sysRoleDO.roleName) &&
                Objects.equals(deleted, sysRoleDO.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roleName, deleted);
    }

    @Override
    public String toString() {
        return "SysRoleDO{" +
                "roleName='" + roleName + '\'' +
                ", deleted=" + deleted +
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