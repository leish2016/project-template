package com.touzhijia.project.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author leish
 * @date 2017/10/27 14:17
 */
@Aspect
@Component
@Slf4j
public class LogHandler {
    @Pointcut("execution(* com.lsh.project.controller.*.*(..))")
    public void log() {
    }

    @Around("log()")
    public Object showLog(ProceedingJoinPoint joinpoint) {
        Object result = null;
        try {
            //记录请求日志
            log.info("{} params {}",joinpoint.toString(),joinpoint.getArgs());
            // 执行原始方法并获取返回值
            result = joinpoint.proceed();
        } catch (Throwable t) {
            log.error("{} params {};err:{}",joinpoint.toString(),joinpoint.getArgs(),t.getMessage());
            BizException.throwBizException(t.getMessage());
        }
        return result;
    }
}
