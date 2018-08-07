package com.aoineko.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by aoineko on 2018/7/16.
 */

@Aspect
@Component
public class LoginAop {
    public static final Logger logger = LoggerFactory.getLogger(LoginAop.class);

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestPointCut() {

    }

    @Around("requestPointCut()")
    public Object requestAround(ProceedingJoinPoint joinPoint) {

        logger.info("around requestPointcut");
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }


}
