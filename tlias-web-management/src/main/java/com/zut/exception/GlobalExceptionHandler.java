package com.zut.exception;

import com.zut.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
// 全局异常处理器
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
   public Result  handleException(Exception e){
       log.error("程序运行出错。。。",e);
       return Result.error("该操作系统错误，请联系管理员");
   }
}
