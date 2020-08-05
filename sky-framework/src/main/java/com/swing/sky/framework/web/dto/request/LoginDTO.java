//package com.swing.sky.framework.web.dto.request;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//
///**
// * 登录传输对象
// *
// * @author swing
// */
//@ApiModel(description = "登录用户信息")
//public class LoginDTO {
//    /**
//     * 用户名
//     */
//    @ApiModelProperty(name = "username", value = "登录用户名", required = true, dataType = "String")
//    @NotNull(message = "用户账户名不为null")
//    @Size(min = 4, max = 20, message = "用户名的长度应介于4~20之间")
//    private String username;
//
//    /**
//     * 用户密码
//     */
//    @ApiModelProperty(name = "password", value = "登录密码", required = true, dataType = "String")
//    @NotNull(message = "用户密码不能为null")
//    @Size(min = 6, max = 20, message = "密码的长度应介于6~20之间")
//    private String password;
//
//
//    /**
//     * 验证码
//     */
//    @ApiModelProperty(name = "captcha", value = "验证码", required = true, dataType = "String")
//    @NotNull(message = "验证码不能为null")
//    @NotBlank
//    private String captcha;
//
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getCaptcha() {
//        return captcha;
//    }
//
//    public void setCaptcha(String captcha) {
//        this.captcha = captcha;
//    }
//
//
//
//    @Override
//    public String toString() {
//        return "LoginDTO{" +
//                "username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", code='" + captcha + '\'' +
//                '}';
//    }
//}
