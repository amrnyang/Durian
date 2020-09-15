package com.swing.sky.system.module.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swing.sky.system.module.annotation.Excel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * sys_log_operate
 * 操作日志
 *
 * @author swing
 */
public class SysLogOperateDO implements Serializable {
    /**
     * 日志主键
     */
    @Excel(name = "ID")
    private Long id;

    /**
     * 操作者
     */
    @Excel(name = "操作者")
    private String operater;

    /**
     * 操作模块名
     */
    @Excel(name = "操作模块名")
    private String module;

    /**
     * 业务类型（I新增 U修改 D删除 O 其他）
     */
    @Excel(name = "业务类型")
    private String businessType;

    /**
     * 请求方式
     */
    @Excel(name = "请求方式")
    private String requestMethod;

    /**
     * 请求URL
     */
    @Excel(name = "请求URL")
    private String requestUrl;

    /**
     * 请求参数
     */
    @Excel(name = "请求参数")
    private String requestParam;

    /**
     * 结果
     */
    @Excel(name = "结果")
    private String result;

    /**
     * 操作成功与否（1成功 0失败）
     */
    @Excel(name = "操作成功与否", readConverterExp = "true=成功,false=失败")
    private Boolean success;

    /**
     * 响应消息
     */
    @Excel(name = "响应消息")
    private String message;

    /**
     * java方法名称
     */
    @Excel(name = "java方法名称")
    private String javaMethod;

    /**
     * 客户端类别（A其它 B后台用户 C手机端用户）
     */
    @Excel(name = "客户端类别", readConverterExp = "A=其他,B=后台用户,C=手机端用户")
    private String clientType;

    /**
     * 主机地址
     */
    @Excel(name = "IP")
    private String ip;

    /**
     * 操作地点
     */
    @Excel(name = "操作地点")
    private String location;

    /**
     * 操作系统
     */
    @Excel(name = "操作系统")
    private String os;

    /**
     * 浏览器
     */
    @Excel(name = "浏览器")
    private String browser;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "操作时间", dateFormat = "yyyy-MM-dd")
    private Date createTime;

    public SysLogOperateDO() {
    }

    public SysLogOperateDO(Long id, String operater, String module, String businessType, String requestMethod, String requestUrl, String requestParam, String result, Boolean success, String message, String javaMethod, String clientType, String ip, String location, String os, String browser, Date createTime) {
        this.id = id;
        this.operater = operater;
        this.module = module;
        this.businessType = businessType;
        this.requestMethod = requestMethod;
        this.requestUrl = requestUrl;
        this.requestParam = requestParam;
        this.result = result;
        this.success = success;
        this.message = message;
        this.javaMethod = javaMethod;
        this.clientType = clientType;
        this.ip = ip;
        this.location = location;
        this.os = os;
        this.browser = browser;
        this.createTime = createTime;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public String getJavaMethod() {
        return javaMethod;
    }

    public void setJavaMethod(String javaMethod) {
        this.javaMethod = javaMethod;
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
        if (!(o instanceof SysLogOperateDO)) {
            return false;
        }
        SysLogOperateDO that = (SysLogOperateDO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(operater, that.operater) &&
                Objects.equals(module, that.module) &&
                Objects.equals(businessType, that.businessType) &&
                Objects.equals(requestMethod, that.requestMethod) &&
                Objects.equals(requestUrl, that.requestUrl) &&
                Objects.equals(requestParam, that.requestParam) &&
                Objects.equals(result, that.result) &&
                Objects.equals(success, that.success) &&
                Objects.equals(message, that.message) &&
                Objects.equals(javaMethod, that.javaMethod) &&
                Objects.equals(clientType, that.clientType) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(location, that.location) &&
                Objects.equals(os, that.os) &&
                Objects.equals(browser, that.browser) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operater, module, businessType, requestMethod, requestUrl, requestParam, result, success, message, javaMethod, clientType, ip, location, os, browser, createTime);
    }

    @Override
    public String toString() {
        return "SysLogOperateDO{" +
                "id=" + id +
                ", operater='" + operater + '\'' +
                ", module='" + module + '\'' +
                ", businessType='" + businessType + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", requestParam='" + requestParam + '\'' +
                ", result='" + result + '\'' +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", javaMethod='" + javaMethod + '\'' +
                ", clientType='" + clientType + '\'' +
                ", ip='" + ip + '\'' +
                ", location='" + location + '\'' +
                ", os='" + os + '\'' +
                ", browser='" + browser + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}