package com.swing.sky.oss;


import com.swing.sky.common.web.SkyResponse;
import org.springframework.http.HttpStatus;
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
        return SkyResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
