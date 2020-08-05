package com.swing.sky.module.system.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * sys_user_role
 * 用户角色关联
 *
 * @author swing
 */
public class SysUserRoleLinkDO implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;


    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public SysUserRoleLinkDO() {
    }

    public SysUserRoleLinkDO(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUserRoleLinkDO)) {
            return false;
        }
        SysUserRoleLinkDO linkDO = (SysUserRoleLinkDO) o;
        return Objects.equals(userId, linkDO.userId) &&
                Objects.equals(roleId, linkDO.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }

    @Override
    public String toString() {
        return "SysUserRoleLinkDO{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}