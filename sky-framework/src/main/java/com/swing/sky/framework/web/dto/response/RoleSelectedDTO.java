package com.swing.sky.framework.web.dto.response;

import com.swing.sky.module.system.domain.SysRoleDO;

/**
 * 角色选择信息传输对象
 *
 * @author swing
 */
public class RoleSelectedDTO extends SysRoleDO {
    /**
     * 该岗位是否被选中
     */
    private Boolean checked;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public RoleSelectedDTO(SysRoleDO role) {
        super(role.getUse(), role.getOrderNum(), role.getCreateBy(), role.getCreateTime(), role.getUpdateBy(), role.getUpdateTime(), role.getRemark(), role.getRoleName(), role.getDeleted());
        this.id = role.getId();
    }

    public RoleSelectedDTO() {
    }

    @Override
    public String toString() {
        return "RoleSelectedDTO{" +
                "checked=" + checked +
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
