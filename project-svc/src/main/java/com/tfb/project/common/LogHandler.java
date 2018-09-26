package com.tfb.project.common;

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
    @Pointcut("execution(* com.tfb.project.controller.*.*(..))")
    public void log() {
    }

    @Around("log()")
    public Object showLog(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        String clazz = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        StringBuilder param = new StringBuilder();
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (Object arg : joinPoint.getArgs()) {
                if (param.length() > 0) {
                    param.append(", ");
                }
                param.append(arg.getClass().getSimpleName());
                param.append('=').append(arg.toString());
            }
        }
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.info("# 请求处理时间 [{}s] -> [{}.{}({})]", (System.currentTimeMillis() - start) / 1000.0, clazz, method, param.toString());
            throw e;
        }
        log.info("# 请求处理时间 [{}s] -> [{}.{}({})]", (System.currentTimeMillis() - start) / 1000.0, clazz, method, param.toString());
        return result;
    }
}
