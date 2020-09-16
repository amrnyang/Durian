package com.swing.sky.system.module.domain;

import com.swing.sky.common.basic.BasicDO;
import com.swing.sky.common.utils.RegexUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * sys_dept
 *
 * @author swing
 */
public class SysDeptDO extends BasicDO implements Serializable {

    private static final long serialVersionUID = -1956639340011155832L;
    /**
     * 父部门id
     */
    private Long parentId;

    /**
     * 部门名称
     */
    @NotNull(message = "部门名称不为null")
    @Size(min = 1, max = 50, message = "部门名称的长度应介于4~20之间")
    private String deptName;

    /**
     * 负责人
     */
    @NotNull(message = "负责人不为null")
    private String leader;

    /**
     * 联系电话
     */
    @NotNull(message = "联系电话不为null")
    @Pattern(regexp = RegexUtils.REGEX_MOBILE_EXACT, message = "电话格式错误")
    private String phone;

    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不为null")
    @Email(message = "邮箱格式不正确")
    private String email;


    public SysDeptDO(@NotNull Boolean use, Integer orderNum, String createBy, Date createTime, String updateBy, Date updateTime, String remark, Long parentId, @NotNull(message = "部门名称不为null") @Size(min = 1, max = 50, message = "部门名称的长度应介于4~20之间") String deptName, @NotNull(message = "负责人不为null") String leader, @NotNull(message = "联系电话不为null") @Pattern(regexp = RegexUtils.REGEX_MOBILE_EXACT, message = "电话格式错误") String phone, @NotNull(message = "邮箱不为null") @Email(message = "邮箱格式不正确") String email) {
        super(use, orderNum, createBy, createTime, updateBy, updateTime, remark);
        this.parentId = parentId;
        this.deptName = deptName;
        this.leader = leader;
        this.phone = phone;
        this.email = email;
    }

    public SysDeptDO() {
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysDeptDO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SysDeptDO sysDeptDO = (SysDeptDO) o;
        return Objects.equals(parentId, sysDeptDO.parentId) &&
                Objects.equals(deptName, sysDeptDO.deptName) &&
                Objects.equals(leader, sysDeptDO.leader) &&
                Objects.equals(phone, sysDeptDO.phone) &&
                Objects.equals(email, sysDeptDO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), parentId, deptName, leader, phone, email);
    }

    @Override
    public String toString() {
        return "SysDeptDO{" +
                "parentId=" + parentId +
                ", deptName='" + deptName + '\'' +
                ", leader='" + leader + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
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