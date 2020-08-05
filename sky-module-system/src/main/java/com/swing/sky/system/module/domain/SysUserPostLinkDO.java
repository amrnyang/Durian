package com.swing.sky.system.module.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * sys_user_post
 *
 * @author swing
 */
public class SysUserPostLinkDO implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 岗位ID
     */
    private Long postId;

    public SysUserPostLinkDO() {
    }

    public SysUserPostLinkDO(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUserPostLinkDO)) {
            return false;
        }
        SysUserPostLinkDO that = (SysUserPostLinkDO) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(postId, that.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId);
    }

    @Override
    public String toString() {
        return "SysUserPostLinkDO{" +
                "userId=" + userId +
                ", postId=" + postId +
                '}';
    }
}