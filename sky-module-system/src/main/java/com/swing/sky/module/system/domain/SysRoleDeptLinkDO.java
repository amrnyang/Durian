package com.swing.sky.module.system.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * sys_role_dept
 *
 * @author swing
 */
public class SysRoleDeptLinkDO implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;

    public SysRoleDeptLinkDO() {
    }

    public SysRoleDeptLinkDO(Long roleId, Long deptId) {
        this.roleId = roleId;
        this.deptId = deptId;
    }

    private static final long serialVersionUID = 1L;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRoleDeptLinkDO)) {
            return false;
        }
        SysRoleDeptLinkDO that = (SysRoleDeptLinkDO) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(deptId, that.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, deptId);
    }

    @Override
    public String toString() {
        return "SysRoleDeptLinkDO{" +
                "roleId=" + roleId +
                ", deptId=" + deptId +
                '}';
    }
}