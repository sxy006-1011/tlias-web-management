package com.itheima.exception;


import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionalHandler {
@ExceptionHandler
    public Result handleException(Exception e){
        log.error("异常信息：{}",e.getMessage());
        return Result.error("服务器错误");
    }
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("异常信息：{}",e);
        String message=e.getMessage();
        int i =message.indexOf("Duplicate entry");
        String errMsg=message.substring(i);
        String[] arr=errMsg.split(" ");
        return Result.error(arr[2]+"已存在");
    }
}
