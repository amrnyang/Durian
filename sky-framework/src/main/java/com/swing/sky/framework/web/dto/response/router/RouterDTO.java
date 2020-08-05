package com.swing.sky.framework.web.dto.response.router;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * 路由目录信息 D
 * 依据前端使用 Vue(ElementUI)需要的数据结构设计
 * 如使用其他前端，此处需要重新设计
 *
 * @author swing
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterDTO {
    /**
     * 路由名字
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    private String hidden;

    /**
     * 重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
     */
    private String redirect;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
     */
    private Boolean alwaysShow;

    /**
     * 其他元素
     */
    private Meta meta;

    /**
     * 子路由
     */
    private List<RouterDTO> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Boolean getAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(Boolean alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<RouterDTO> getChildren() {
        return children;
    }

    public void setChildren(List<RouterDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "RouterDTO{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", hidden='" + hidden + '\'' +
                ", redirect='" + redirect + '\'' +
                ", component='" + component + '\'' +
                ", alwaysShow=" + alwaysShow +
                ", meta=" + meta +
                ", children=" + children +
                '}';
    }
}
