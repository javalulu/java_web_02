package com.americano.exeption;

import com.americano.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
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

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("程序出错", e);
        // 截取错误信息
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        // 找到重复的key值
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + "已存在");
    }
}
