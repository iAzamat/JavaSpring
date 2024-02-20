package ru.gb.homework9.storeutils.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Date;
import java.util.stream.Stream;

@Aspect
@Slf4j
public class LogAspect {
    @Around("@annotation(ru.gb.*.annotations.MyLog)")
    public Object logMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            String timeStamp = new Date().toString();
            log.info(timeStamp + " Method " + joinPoint.getSignature().getName() + " started");
            log.info(timeStamp + "    params: ");
            Stream.of(joinPoint.getArgs())
                    .forEach(arg -> {
                        if (arg != null) {
                            log.info(timeStamp + "      " + arg);
                        } else {
                            log.info(timeStamp + "      " + null);
                        }
                    });

            Object result = joinPoint.proceed();

            log.info(timeStamp + " Method " + joinPoint.getSignature().getName() + " successfully complete");
            log.info(timeStamp + "    result: ");
            if (result != null) {
                log.info(timeStamp + "        " + result);
            } else {
                log.info(timeStamp + "        " + null);
            }

            return result;
        } catch (Throwable ex) {
            throw ex;
        }
    }
}
