package com.swing.sky.system.module.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swing.sky.system.module.annotation.Excel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * sys_log_login
 * 登录日志
 *
 * @author swing
 */
public class SysLogLoginDO implements Serializable {
    /**
     * 访问ID
     */
    @Excel(name = "ID")
    private Long id;

    /**
     * 用户账号
     */
    @Excel(name = "ID")
    private String username;

    /**
     * 客户端类型
     */
    @Excel(name = "ID")
    private String clientType;

    /**
     * 是否成功（1成功 失败）
     */
    @Excel(name = "登录结果", readConverterExp = "true=成功,false=失败")
    private Boolean success;

    /**
     * 提示消息
     */
    @Excel(name = "提示消息")
    private String message;

    /**
     * 登录IP地址
     */
    @Excel(name = "登录IP地址")
    private String ip;

    /**
     * 登录地点
     */
    @Excel(name = "登录地点")
    private String location;

    /**
     * 操作系统
     */
    @Excel(name = "操作系统")
    private String os;

    /**
     * 浏览器类型
     */
    @Excel(name = "浏览器类型")
    private String browser;

    /**
     * 访问时间
     */
    @Excel(name = "登录时间", dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public SysLogLoginDO(Long id, String username, String clientType, Boolean success, String message, String ip, String location, String os, String browser, Date createTime) {
        this.id = id;
        this.username = username;
        this.clientType = clientType;
        this.success = success;
        this.message = message;
        this.ip = ip;
        this.location = location;
        this.os = os;
        this.browser = browser;
        this.createTime = createTime;
    }

    public SysLogLoginDO() {
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysLogLoginDO)) {
            return false;
        }
        SysLogLoginDO that = (SysLogLoginDO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(clientType, that.clientType) &&
                Objects.equals(success, that.success) &&
                Objects.equals(message, that.message) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(location, that.location) &&
                Objects.equals(os, that.os) &&
                Objects.equals(browser, that.browser) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, clientType, success, message, ip, location, os, browser, createTime);
    }

    @Override
    public String toString() {
        return "SysLogLoginDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", clientType='" + clientType + '\'' +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", ip='" + ip + '\'' +
                ", location='" + location + '\'' +
                ", os='" + os + '\'' +
                ", browser='" + browser + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}