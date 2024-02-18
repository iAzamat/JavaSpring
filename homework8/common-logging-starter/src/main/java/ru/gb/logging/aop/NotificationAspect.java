package ru.gb.logging.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Date;

@Slf4j
@Aspect
public class NotificationAspect {
    @Around(value = "execution(* ru.*.*.service.NotificationService.notify(String, int)) && args(msg, level)",
            argNames = "joinPoint,msg,level")
    public Object aroundNotify(ProceedingJoinPoint joinPoint, String msg, int level) throws Throwable {
        try {
            String timeStamp = new Date().toString();
            switch (level) {
                case 1 -> log.info(timeStamp + " : " + msg);
                case 2 -> log.warn(timeStamp + " : " + msg);
                case 3 -> log.error(timeStamp + " : " + msg);
            }
            return joinPoint.proceed();
        } catch (Throwable ex) {
            throw ex;
        }
    }
}
