package com.swing.sky.system.module.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swing.sky.system.module.annotation.Excel;
import com.swing.sky.common.basic.BasicDO;
import com.swing.sky.common.utils.RegexUtils;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 后台用户
 * sys_user
 *
 * @author swing
 */
public class SysUserDO extends BasicDO implements Serializable {
    /**
     * 部门id
     */
    @Excel(name = "部门id")
    private Long deptId;

    /**
     * 用户名
     */
    @Excel(name = "用户名")
    @NotNull(message = "用户账户名不为null")
    @Size(min = 4, max = 20, message = "用户名的长度应介于4~20之间")
    private String username;

    /**
     * 密码
     */
    @Size(min = 6, max = 20, message = "密码的长度应介于6~20之间")
    @JsonIgnore
    private String password;

    /**
     * 用户昵称
     */
    @Excel(name = "用户昵称")
    @Size(min = 1, max = 20, message = "昵称的长度应介于1~20之间")
    private String nickName;

    /**
     * 用户邮箱
     */
    @Excel(name = "用户邮箱")
    @NotBlank
    @NotNull(message = "用户邮箱不能为null")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    @NotBlank
    @NotNull(message = "用户手机号不能为null")
    @Pattern(regexp = RegexUtils.REGEX_MOBILE_EXACT, message = "电话格式错误")
    private String phone;

    /**
     * 用户性别（M男 W女 N未知）
     */
    @Excel(name = "用户性别", readConverterExp = "M=男,W=女,N=未知")
    @NotNull(message = "性别不能为null")
    @NotBlank
    private String gender;

    /**
     * 头像地址
     */
    @Excel(name = "头像地址")
    private String avatar;

    /**
     * 是否删除 (1 删除，0 未删除）
     */
    @Excel(name = "是否删除", readConverterExp = "true=是,false=否")
    private Boolean deleted;

    public SysUserDO(@NotNull Boolean use, Integer orderNum, String createBy, Date createTime, String updateBy, Date updateTime, String remark, Long deptId, @NotNull(message = "用户账户名不为null") @Size(min = 4, max = 20, message = "用户名的长度应介于4~20之间") String username, @NotNull(message = "用户密码不能为null") @Size(min = 6, max = 20, message = "密码的长度应介于6~20之间") String password, @Size(min = 1, max = 20, message = "昵称的长度应介于1~20之间") String nickName, @NotBlank @NotNull(message = "用户邮箱不能为null") @Email(message = "邮箱格式不正确") String email, @NotBlank @NotNull(message = "用户手机号不能为null") @Pattern(regexp = RegexUtils.REGEX_MOBILE_EXACT, message = "电话格式错误") String phone, @NotNull(message = "性别不能为null") @NotBlank String gender, String avatar, Boolean deleted) {
        super(use, orderNum, createBy, createTime, updateBy, updateTime, remark);
        this.deptId = deptId;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.avatar = avatar;
        this.deleted = deleted;
    }

    public SysUserDO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUserDO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SysUserDO sysUserDO = (SysUserDO) o;
        return Objects.equals(deptId, sysUserDO.deptId) &&
                Objects.equals(username, sysUserDO.username) &&
                Objects.equals(password, sysUserDO.password) &&
                Objects.equals(nickName, sysUserDO.nickName) &&
                Objects.equals(email, sysUserDO.email) &&
                Objects.equals(phone, sysUserDO.phone) &&
                Objects.equals(gender, sysUserDO.gender) &&
                Objects.equals(avatar, sysUserDO.avatar) &&
                Objects.equals(deleted, sysUserDO.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deptId, username, password, nickName, email, phone, gender, avatar, deleted);
    }

    @Override
    public String toString() {
        return "SysUserDO{" +
                "deptId=" + deptId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                ", deleted=" + deleted +
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