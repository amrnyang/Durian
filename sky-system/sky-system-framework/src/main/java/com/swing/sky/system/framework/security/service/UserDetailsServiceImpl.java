package com.swing.sky.system.framework.security.service;

import com.swing.sky.system.framework.security.exception.UserNotFountException;
import com.swing.sky.system.framework.security.exception.UserUnUsedException;
import com.swing.sky.system.module.domain.SysUserDO;
import com.swing.sky.system.module.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author swing
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Resource
    private SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDO user = userService.getUserByUsername(username);
        if (user == null) {
            log.info("您输入的用户:{} 不存在!", username);
            throw new UserNotFountException(username + "：用户名或密码错误，请重新输入");
        }
        if (!user.getUse()) {
            log.info("您输入的用户:{} 已停用!", username);
            throw new UserUnUsedException(username + "：该用户已被管理员禁用！");
        }
        return new UserDetailsImpl(user);
    }
}
