package com.swing.sky.center;


import com.swing.sky.common.web.SkyResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.lettuce.core.RedisConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理中心
 *
 * @author swing
 */
@RestControllerAdvice
public class CenterExceptionHandler {
    /**
     * 所有的自行抛出的异常（默认错误状态500)
     */
    @ExceptionHandler(RuntimeException.class)
    SkyResponse exception(RuntimeException e) {
        e.printStackTrace();
        return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    /**
     * 令牌过期异常
     */
    @ExceptionHandler(ExpiredJwtException.class)
    SkyResponse expiredJwtException(ExpiredJwtException e) {
        return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "令牌过期");
    }

    /**
     * 令牌无效
     */
    @ExceptionHandler(JwtException.class)
    SkyResponse signatureException(JwtException e) {
        return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "令牌无效");
    }

    /**
     * 拦截未通过 @Validated 所产生的异常
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(BindException.class)
    SkyResponse exception(BindException e) {
        return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * redis连接异常
     */
    @ExceptionHandler(RedisConnectionException.class)
    SkyResponse redisConnectionException(RedisConnectionException e) {
        return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "redis连接异常");
    }
}
