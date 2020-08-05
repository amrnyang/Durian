package com.swing.sky.system.framework.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义用户未找到异常
 *
 * @author swing
 */
public class UserNotFountException extends AuthenticationException {
    public UserNotFountException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotFountException(String msg) {
        super(msg);
    }
}
