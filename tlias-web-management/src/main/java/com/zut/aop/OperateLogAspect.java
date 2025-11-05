package com.zut.aop;

import com.zut.mapper.OperateLogMapper;
import com.zut.pojo.OperateLog;
import com.zut.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jfr.Category;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/*
 * 操作日志切面类
 */
@Slf4j
@Aspect
@Component
public class OperateLogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    //底层自动配置的
    @Autowired
    private HttpServletRequest request;
    @Around("@annotation(com.zut.anno.Log)")
    public Object recordLog(ProceedingJoinPoint pjp) throws Throwable {

        //1.获取当前登录的操作人id  令牌里面有基础信息  请求头token中获取令牌
         String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer empId  = (Integer) claims.get("id");
         String operateEmpName = (String) claims.get("username");
        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        //操作类名
        String className = pjp.getTarget().getClass().getName();
        //操作的方法名
        String methodName = pjp.getSignature().getName();
        //方法运行的参数
        String methodParams = Arrays.toString(pjp.getArgs());

        long begin  =System.currentTimeMillis();
        //调用原始方法运行
        Object result = pjp.proceed();
        long end= System.currentTimeMillis();
        OperateLog  operateLog = new OperateLog(null,empId,operateTime,className,methodName,methodParams,result.toString(),end-begin,operateEmpName);
        //最后插入数据到数据库表
        operateLogMapper.insert(operateLog);
        return result;
    }

}

