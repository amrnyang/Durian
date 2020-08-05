package com.swing.sky.module.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * sys_user_online
 * 在线用户信息
 *
 * @author swing
 */
public class SysUserOnlineDO implements Serializable {

    /**
     * id
     */
    private Long id;
    /**
     * 用户会话id
     */
    private String sessionId;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 客户端类别
     */
    private String clientType;

    /**
     * 登录IP地址
     */
    private String ip;

    /**
     * 登录地点
     */
    private String location;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 是否在线(1:在线，0:离线）
     */
    private Boolean online;

    /**
     * session创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * session最后访问时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastAccessTime;

    /**
     * 超时时间，单位为分钟
     */
    private Integer expireTime;

    public SysUserOnlineDO() {
    }

    public SysUserOnlineDO(Long id, String sessionId, String username, String clientType, String ip, String location, String os, String browser, Boolean online, Date createTime, Date lastAccessTime, Integer expireTime) {
        this.id = id;
        this.sessionId = sessionId;
        this.username = username;
        this.clientType = clientType;
        this.ip = ip;
        this.location = location;
        this.os = os;
        this.browser = browser;
        this.online = online;
        this.createTime = createTime;
        this.lastAccessTime = lastAccessTime;
        this.expireTime = expireTime;
    }

    private static final long serialVersionUID = 1L;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysUserOnlineDO that = (SysUserOnlineDO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(sessionId, that.sessionId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(clientType, that.clientType) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(location, that.location) &&
                Objects.equals(os, that.os) &&
                Objects.equals(browser, that.browser) &&
                Objects.equals(online, that.online) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(lastAccessTime, that.lastAccessTime) &&
                Objects.equals(expireTime, that.expireTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, username, clientType, ip, location, os, browser, online, createTime, lastAccessTime, expireTime);
    }

    @Override
    public String toString() {
        return "SysUserOnlineDO{" +
                "id=" + id +
                ", sessionId='" + sessionId + '\'' +
                ", username='" + username + '\'' +
                ", clientType='" + clientType + '\'' +
                ", ip='" + ip + '\'' +
                ", location='" + location + '\'' +
                ", os='" + os + '\'' +
                ", browser='" + browser + '\'' +
                ", online=" + online +
                ", createTime=" + createTime +
                ", lastAccessTime=" + lastAccessTime +
                ", expireTime=" + expireTime +
                '}';
    }
}