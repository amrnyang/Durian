package com.swing.sky.framework.web.dto.request;

import com.swing.sky.module.system.domain.SysRoleDO;

import java.util.Arrays;

/**
 * 角色信息传输对象
 *
 * @author swing
 */
public class RoleDTO extends SysRoleDO {
    /**
     * 角色菜单关联
     */
    private Long[] menuIds;
    /**
     * 角色部门关联
     */
    private Long[] deptIds;

    public SysRoleDO getSysRoleDO() {
        return new SysRoleDO(use, orderNum, createBy, createTime, updateBy, updateTime, remark, getRoleName(), getDeleted());
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "menuIds=" + Arrays.toString(menuIds) +
                ", deptIds=" + Arrays.toString(deptIds) +
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
        if (!(o instanceof RoleDTO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        RoleDTO roleDTO = (RoleDTO) o;
        return Arrays.equals(menuIds, roleDTO.menuIds) &&
                Arrays.equals(deptIds, roleDTO.deptIds);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(menuIds);
        result = 31 * result + Arrays.hashCode(deptIds);
        return result;
    }

    public Long[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds) {
        this.menuIds = menuIds;
    }

    public Long[] getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(Long[] deptIds) {
        this.deptIds = deptIds;
    }
}
