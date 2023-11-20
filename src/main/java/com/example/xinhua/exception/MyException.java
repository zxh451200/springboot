package com.example.xinhua.exception;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.xinhua.pojo.Result;

@RestControllerAdvice
public class MyException {
    @ExceptionHandler(Exception.class)
    public Result handleExceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败");
    }
}
