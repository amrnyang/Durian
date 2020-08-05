package com.swing.sky.system.api.dto.response.tree;

import java.io.Serializable;

/**
 * 树结构实体类(适用于勾选部门，菜单等数据）
 *
 * @author swing
 */
public class TreeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long id;

    /**
     * 节点父ID
     */
    private Long pId;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 节点标题
     */
    private String title;

    /**
     * 是否勾选
     */
    private Boolean checked;

    /**
     * 是否展开
     */
    private Boolean open;

    /**
     * 是否能勾选
     */
    private Boolean nocheck;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getNocheck() {
        return nocheck;
    }

    public void setNocheck(Boolean nocheck) {
        this.nocheck = nocheck;
    }

    @Override
    public String toString() {
        return "TreeDTO{" +
                "id=" + id +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", checked=" + checked +
                ", open=" + open +
                ", nocheck=" + nocheck +
                '}';
    }
}
