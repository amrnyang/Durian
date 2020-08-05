package com.swing.sky.framework.web.dto.response.tree;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

/**
 * 所有以展示树结构显示的信息的模板类
 * @author swing
 */
public class ShowTreeDTO {
    /**
     * 节点ID
     */
    private Long id;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ShowTreeDTO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Byte getUse() {
        return use;
    }

    public void setUse(Byte use) {
        this.use = use;
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

    public List<ShowTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ShowTreeDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ShowTreeDTO{" +
                "id=" + id +
                ", nodeName='" + nodeName + '\'' +
                ", use=" + use +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", children=" + children +
                '}';
    }
}
