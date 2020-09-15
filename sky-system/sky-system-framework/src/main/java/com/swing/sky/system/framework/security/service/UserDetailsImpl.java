package com.swing.sky.system.framework.security.service;

import com.swing.sky.system.module.domain.SysUserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 此信息在认证成功后将作为 authentication 中的 principal
 *
 * @author swing
 */
public class UserDetailsImpl implements UserDetails {
    /**
     * 数据库用户信息(将其包装在userDetails中)
     */
    private SysUserDO userDO;

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(SysUserDO userDO) {
        this.userDO = userDO;
    }

    /**
     * 返回授予用户的权限
     *
     * @return 权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userDO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDO.getUsername();
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public SysUserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(SysUserDO userDO) {
        this.userDO = userDO;
    }
}
