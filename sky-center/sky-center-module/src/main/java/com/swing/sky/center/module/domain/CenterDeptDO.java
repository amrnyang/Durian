package com.swing.sky.center.module.domain;

import com.swing.sky.common.basic.BasicDO;

import java.io.Serializable;

/**
 * 部门表:对象 center_dept
 *
 * @author swing
 */
public class CenterDeptDO extends BasicDO implements Serializable{
    private static final long serialVersionUID=1L;
    /**
     * 父部门id
     */
    private Long parentId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 负责人
     */
    private String leader;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 无参构造函数
     */
    public CenterDeptDO() {
    }

    /**
     * 全参构造函数
     */
    public CenterDeptDO(Long parentId, String deptName, String leader, String phone, String email) {
        this.parentId = parentId;
        this.deptName = deptName;
        this.leader = leader;
        this.phone = phone;
        this.email = email;
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
    public String toString() {
        return "CenterDeptDO{" +
                "parentId=" + parentId + ',' +
                "deptName=" + deptName + ',' +
                "leader=" + leader + ',' +
                "phone=" + phone + ',' +
                "email=" + email  +
                '}'+super.toString();
    }
}