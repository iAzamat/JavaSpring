package ru.gb.homework9.storeutils.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Slf4j
public class PerformanceAspect {
    @Around("@annotation(ru.gb.homework9.storeutils.annotations.MyPerformance)")
    public Object logMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String timeStamp = new Date().toString();
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        log.info(timeStamp + " Method " + joinPoint.getSignature().getName() + " complete for " + elapsedTime + " ms");
        return result;
    }
}
