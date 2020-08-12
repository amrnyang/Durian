package com.swing.sky.common.web;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一的响应格式
 *
 * @author swing
 */
public class SkyResponse {
    /**
     * 状态码
     */
    @ApiModelProperty(name = "status", value = "状态码")
    private Integer status;
    /**
     * 响应信息
     */
    @ApiModelProperty(name = "msg", value = "响应信息")
    private String msg;
    /**
     * 响应体
     */
    @ApiModelProperty(name = "body", value = "响应数据集")
    private Map<String, Object> body;

    @Override
    public String toString() {
        return "RestResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", body=" + body +
                '}';
    }

    /**
     * 返回消息和状态码
     */
    public static SkyResponse getInstance(HttpStatus status, String message) {
        return new SkyResponse(status.value(), message, null);
    }

    /**
     * 返回成功的消息（无响应体）
     */
    public static SkyResponse success() {
        return new SkyResponse(HttpStatus.OK.value(), "请求成功！", null);
    }

    /**
     * 返回成功的消息（有响应体）
     *
     * @param length body 信息的长度
     */
    public static SkyResponse success(int length) {
        return new SkyResponse(HttpStatus.OK.value(), "请求成功！", new HashMap<>(length));
    }

    /**
     * 返回成功的消息（无响应体）（自定义消息）
     */
    public static SkyResponse success(String message) {
        return new SkyResponse(HttpStatus.OK.value(), message, null);
    }


    /**
     * 返回成功的消息（有响应体）(自定义消息）
     *
     * @param length body 信息的长度
     */
    public static SkyResponse success(String message, int length) {
        return new SkyResponse(HttpStatus.OK.value(), message, new HashMap<>(length));
    }

    /**
     * 返回错误消息
     *
     * @param status 状态码
     * @param msg    错误消息
     * @return 结果
     */
    public static SkyResponse fail(HttpStatus status, String msg) {
        return new SkyResponse(status.value(), msg, null);
    }

    /**
     * 链式的将响应的结果放入body中
     */
    public SkyResponse put(String key, Object value) {
        this.body.put(key, value);
        return this;
    }


    public SkyResponse() {
    }

    public SkyResponse(Integer status, String msg, Map<String, Object> body) {
        this.status = status;
        this.msg = msg;
        this.body = body;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }
}
