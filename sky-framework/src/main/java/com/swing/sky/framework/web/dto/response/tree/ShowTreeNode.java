package com.swing.sky.framework.web.dto.response.tree;

import java.util.Date;

/**
 * 构建展示树的结点
 * @author swing
 */
public class ShowTreeNode {
    /**
     * 节点ID
     */
    private Long id;

    /**
     * 父节点ID
     */
    private Long parentId;
    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 数据是否使用（1 使用；2 停用）
     */
    private Byte use;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    public ShowTreeNode(Long id, Long parentId, String nodeName, Byte use, String createBy, Date createTime) {
        this.id = id;
        this.parentId = parentId;
        this.nodeName = nodeName;
        this.use = use;
        this.createBy = createBy;
        this.createTime = createTime;
    }

    public ShowTreeNode() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getUse() {
        return use;
    }

    public void setUse(Byte use) {
        this.use = use;
    }

    @Override
    public String toString() {
        return "ShowTreeNode{" +
                "id=" + id +
                ", parent_id=" + parentId +
                ", nodeName='" + nodeName + '\'' +
                ", use=" + use +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
