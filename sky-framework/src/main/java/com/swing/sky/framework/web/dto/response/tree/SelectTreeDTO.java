package com.swing.sky.framework.web.dto.response.tree;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * 所有以选择树结构显示的信息的模板类
 *
 * @author swing
 */
public class SelectTreeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SelectTreeDTO> children;

    public SelectTreeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<SelectTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<SelectTreeDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SelectTreeDTO{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", children=" + children +
                '}';
    }
}
