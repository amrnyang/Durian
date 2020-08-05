package com.swing.sky.module.system.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * sys_role_menu
 * 角色菜单关联
 *
 * @author swing
 */
public class SysRoleMenuLinkDO implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;


    private static final long serialVersionUID = 1L;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public SysRoleMenuLinkDO() {
    }

    public SysRoleMenuLinkDO(Long roleId, Long menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRoleMenuLinkDO)) {
            return false;
        }
        SysRoleMenuLinkDO that = (SysRoleMenuLinkDO) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(menuId, that.menuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, menuId);
    }

    @Override
    public String toString() {
        return "SysRoleMenuLinkDO{" +
                "roleId=" + roleId +
                ", menuId=" + menuId +
                '}';
    }
}