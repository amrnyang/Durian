package com.swing.sky.framework.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义用户禁用异常
 *
 * @author swing
 */
public class UserUnUsedException extends AuthenticationException {
    public UserUnUsedException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserUnUsedException(String msg) {
        super(msg);
    }
}
