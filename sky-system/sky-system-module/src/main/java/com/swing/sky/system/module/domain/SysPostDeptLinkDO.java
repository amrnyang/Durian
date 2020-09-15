package com.swing.sky.system.module.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * sys_post_dept
 *
 * @author swing
 */
public class SysPostDeptLinkDO implements Serializable {
    /**
     * 岗位id
     */
    private Long postId;

    /**
     * 部门id
     */
    private Long deptId;

    public SysPostDeptLinkDO() {
    }

    public SysPostDeptLinkDO(Long postId, Long deptId) {
        this.postId = postId;
        this.deptId = deptId;
    }

    private static final long serialVersionUID = 1L;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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
        if (!(o instanceof SysPostDeptLinkDO)) {
            return false;
        }
        SysPostDeptLinkDO that = (SysPostDeptLinkDO) o;
        return Objects.equals(postId, that.postId) &&
                Objects.equals(deptId, that.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, deptId);
    }

    @Override
    public String toString() {
        return "SysPostDeptLinkDO{" +
                "postId=" + postId +
                ", deptId=" + deptId +
                '}';
    }
}