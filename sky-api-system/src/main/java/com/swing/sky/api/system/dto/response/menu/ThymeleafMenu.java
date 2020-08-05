package com.swing.sky.api.system.dto.response.menu;

import com.swing.sky.common.basic.BasicDO;
import com.swing.sky.module.system.domain.SysMenuDO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * thymeleaf需要的菜单结构
 *
 * @author swing
 */
public class ThymeleafMenu extends BasicDO implements Serializable {
    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */

    private String menuName;

    /**
     * 菜单类型（D目录 M菜单 B按钮）
     */
    private String menuType;

    /**
     * 权限标识
     */
    private String authoritySign;

    /**
     * 路径
     */
    private String path;

    /**
     * 路径
     */
    private String component;


    /**
     * 打开方式（A：内部标签页 B：浏览器标签页 C：新浏览器窗口）
     */
    private String openWay;

    /**
     * 是否为外链接
     */
    private Boolean externalLink;

    /**
     * 菜单图标
     */
    private String icon;

    List<ThymeleafMenu> children = new ArrayList<>();

    public ThymeleafMenu(SysMenuDO menuDO) {
        this.id = menuDO.getId();
        this.parentId = menuDO.getParentId();
        this.openWay = menuDO.getOpenWay();
        this.externalLink = menuDO.getExternalLink();
        this.icon = menuDO.getIcon();
        this.use = menuDO.getUse();
        this.authoritySign = menuDO.getAuthoritySign();
        this.path = menuDO.getPath();
        this.menuName = menuDO.getMenuName();
        this.menuType = menuDO.getMenuType();
        this.orderNum = menuDO.getOrderNum();
        this.createBy = menuDO.getCreateBy();
        this.createTime = menuDO.getCreateTime();
        this.updateBy = menuDO.getUpdateBy();
        this.updateTime = menuDO.getUpdateTime();
        this.remark = menuDO.getRemark();
    }

    public ThymeleafMenu() {

    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getAuthoritySign() {
        return authoritySign;
    }

    public void setAuthoritySign(String authoritySign) {
        this.authoritySign = authoritySign;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getOpenWay() {
        return openWay;
    }

    public void setOpenWay(String openWay) {
        this.openWay = openWay;
    }

    public Boolean getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(Boolean externalLink) {
        this.externalLink = externalLink;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<ThymeleafMenu> getChildren() {
        return children;
    }

    public void setChildren(List<ThymeleafMenu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ThymeleafMenu{" +
                "parentId=" + parentId +
                ", menuName='" + menuName + '\'' +
                ", menuType='" + menuType + '\'' +
                ", authoritySign='" + authoritySign + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", openWay='" + openWay + '\'' +
                ", externalLink=" + externalLink +
                ", icon='" + icon + '\'' +
                ", children=" + children +
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
        if (!(o instanceof ThymeleafMenu)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ThymeleafMenu that = (ThymeleafMenu) o;
        return Objects.equals(parentId, that.parentId) &&
                Objects.equals(menuName, that.menuName) &&
                Objects.equals(menuType, that.menuType) &&
                Objects.equals(authoritySign, that.authoritySign) &&
                Objects.equals(path, that.path) &&
                Objects.equals(component, that.component) &&
                Objects.equals(openWay, that.openWay) &&
                Objects.equals(externalLink, that.externalLink) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), parentId, menuName, menuType, authoritySign, path, component, openWay, externalLink, icon, children);
    }
}
