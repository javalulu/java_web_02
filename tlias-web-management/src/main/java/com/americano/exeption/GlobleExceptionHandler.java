package com.americano.exeption;

import com.americano.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("程序出错", e);
        return Result.error("出错了！");
    }
}
