package com.example.xinhua.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.xinhua.pojo.Result;

@RestControllerAdvice
public class MyException {
    @ExceptionHandler(Exception.class)
    public Result<String> handleExceptionHandler(Exception e) {
        String errorMessage = e.getMessage();
        if (errorMessage != null && !errorMessage.isEmpty()) {
            return Result.error(errorMessage);
        } else {
            return Result.error("操作失败");
        }
    }
}