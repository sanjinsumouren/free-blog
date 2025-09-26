package com.su.blog.handler;

import com.su.blog.exception.BaseException;
import com.su.blog.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理器
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result<?> handleUnknownException(Exception e) {
        log.error("未知异常：", e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public Result<?> error(BaseException e) {
        log.error("异常信息：{}", e.getMessage());
        return Result.error(e.getMessage());
    }

}
