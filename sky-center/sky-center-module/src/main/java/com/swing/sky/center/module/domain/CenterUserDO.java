package com.swing.sky.center.module.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swing.sky.common.basic.BasicDO;

import java.io.Serializable;

/**
 * 会员信息表:对象 center_user
 *
 * @author swing
 */
public class CenterUserDO extends BasicDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 用户账号（这里使用学生的学号）
     */
    private String username;
    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户校内地址
     */
    private String address;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 用户性别（M男 W女 N未知）
     */
    private String gender;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 是否删除 (1 删除，0 未删除）
     */
    private Boolean deleted;

    /**
     * 无参构造函数
     */
    public CenterUserDO() {
    }

    /**
     * 全参构造函数
     */
    public CenterUserDO(Long deptId, String username, String password, String nickName, String address, String email, String phone, String gender, String avatar, Boolean deleted) {
        this.deptId = deptId;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.avatar = avatar;
        this.deleted = deleted;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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


    @Override
    public String toString() {
        return "CenterUserDO{" +
                "deptId=" + deptId + ',' +
                "username=" + username + ',' +
                "password=" + password + ',' +
                "nickName=" + nickName + ',' +
                "address=" + address + ',' +
                "email=" + email + ',' +
                "phone=" + phone + ',' +
                "gender=" + gender + ',' +
                "avatar=" + avatar + ',' +
                "deleted=" + deleted +
                '}' + super.toString();
    }
}