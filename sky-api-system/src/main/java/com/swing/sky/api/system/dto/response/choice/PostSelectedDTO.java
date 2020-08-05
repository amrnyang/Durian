package com.swing.sky.api.system.dto.response.choice;

import com.swing.sky.module.system.domain.SysPostDO;

/**
 * 岗位选择信息传输对象
 *
 * @author swing
 */
public class PostSelectedDTO extends SysPostDO {
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

    public PostSelectedDTO(SysPostDO post) {
        super(post.getUse(), post.getOrderNum(), post.getCreateBy(), post.getCreateTime(), post.getUpdateBy(), post.getUpdateTime(), post.getRemark(), post.getPostName());
        this.id = post.getId();
    }

    public PostSelectedDTO() {
    }

    @Override
    public String toString() {
        return "PostSelectedDTO{" +
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
