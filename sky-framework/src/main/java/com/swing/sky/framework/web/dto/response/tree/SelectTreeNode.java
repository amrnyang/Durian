package com.swing.sky.framework.web.dto.response.tree;

/**
 * 构建选择树的结点
 * @author swing
 */
public class SelectTreeNode {
    private static final long serialVersionUID = 1L;

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

    public SelectTreeNode(Long id, Long parentId, String nodeName) {
        this.id = id;
        this.parentId = parentId;
        this.nodeName = nodeName;
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

    @Override
    public String toString() {
        return "SelectTreeNode{" +
                "id=" + id +
                ", parent_id=" + parentId +
                ", nodeName='" + nodeName + '\'' +
                '}';
    }
}
