package com.swing.sky.common.utils.security;

import com.swing.sky.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.swing.sky.common.constant.TokenConstant.TOKEN_HEADER_NAME;
import static com.swing.sky.common.constant.TokenConstant.TOKEN_PREFIX_NAME;

/**
 * token处理工具
 * @author JIANG
 * @since 2020-08-28
 */
public class TokenUtils {
    /**
     * 从请求头中得到token
     *
     * @param request 请求
     * @return 如果有token，则返回token，无返回null
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER_NAME);
        if (StringUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX_NAME)) {
            token = token.replace(TOKEN_PREFIX_NAME, "");
            return token;
        }
        return null;
    }
}
