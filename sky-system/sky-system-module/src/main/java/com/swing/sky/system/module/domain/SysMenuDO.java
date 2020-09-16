package com.swing.sky.system.module.domain;

import com.swing.sky.common.basic.BasicDO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * sys_menu
 * 菜单
 *
 * @author swing
 */
public class SysMenuDO extends BasicDO implements Serializable {

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    @NotNull(message = "菜单名称不为null")
    @Size(min = 1, max = 100, message = "菜单名称长度范围在1~100")
    private String menuName;

    /**
     * 菜单类型（D目录 M菜单 B按钮）
     */
    @NotNull(message = "菜单类型不为null")
    @Size(min = 1, max = 1, message = "菜单类型为一个字符")
    private String menuType;

    /**
     * 权限标识
     */
    @Size(max = 100, message = "权限标识长度不大于100")
    private String authoritySign;

    /**
     * 路径
     */
    @Size(max = 1000, message = "页面请求路径长度范围在1~1000")
    private String path;

    /**
     * 路径
     */
    private String component;


    /**
     * 打开方式（A：内部标签页 B：浏览器标签页 C：新浏览器窗口）
     */
    @Size(max = 20, message = "打开方式长度为1-20个字符")
    private String openWay;

    /**
     * 是否为外链接
     */
    private Boolean externalLink;

    /**
     * 菜单图标
     */
    private String icon;

    public SysMenuDO(@NotNull Boolean use, Integer orderNum, String createBy, Date createTime, String updateBy, Date updateTime, String remark, Long parentId, @NotNull(message = "菜单名称不为null") @Size(min = 1, max = 100, message = "菜单名称长度范围在1~100") String menuName, @NotNull(message = "菜单类型不为null") @Size(min = 1, max = 1, message = "菜单类型为一个字符") String menuType, @NotNull(message = "权限标识不为null") @Size(min = 1, max = 100, message = "权限标识长度范围在1~100") String authoritySign, @NotNull(message = "页面请求路径不为null") @Size(min = 1, max = 1000, message = "页面请求路径长度范围在1~1000") String path, String component, @NotNull(message = "打开方式不为null") @Size(min = 1, max = 1, message = "打开方式为一个字符") String openWay, Boolean externalLink, String icon) {
        super(use, orderNum, createBy, createTime, updateBy, updateTime, remark);
        this.parentId = parentId;
        this.menuName = menuName;
        this.menuType = menuType;
        this.authoritySign = authoritySign;
        this.path = path;
        this.component = component;
        this.openWay = openWay;
        this.externalLink = externalLink;
        this.icon = icon;
    }

    public SysMenuDO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysMenuDO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SysMenuDO menuDO = (SysMenuDO) o;
        return Objects.equals(parentId, menuDO.parentId) &&
                Objects.equals(menuName, menuDO.menuName) &&
                Objects.equals(menuType, menuDO.menuType) &&
                Objects.equals(authoritySign, menuDO.authoritySign) &&
                Objects.equals(path, menuDO.path) &&
                Objects.equals(component, menuDO.component) &&
                Objects.equals(openWay, menuDO.openWay) &&
                Objects.equals(externalLink, menuDO.externalLink) &&
                Objects.equals(icon, menuDO.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), parentId, menuName, menuType, authoritySign, path, component, openWay, externalLink, icon);
    }

    @Override
    public String toString() {
        return "SysMenuDO{" +
                "parentId=" + parentId +
                ", menuName='" + menuName + '\'' +
                ", menuType='" + menuType + '\'' +
                ", authoritySign='" + authoritySign + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", openWay='" + openWay + '\'' +
                ", externalLink=" + externalLink +
                ", icon='" + icon + '\'' +
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