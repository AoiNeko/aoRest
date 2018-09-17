package com.aoineko.aop;

import com.aoineko.annotation.Login;
import com.aoineko.constant.ExceptionConstants;
import com.aoineko.entity.Response;
import com.aoineko.exception.ServerException;
import com.aoineko.exception.UserAuthException;
import com.aoineko.service.UserService;
import com.google.common.cache.Cache;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by aoineko on 2018/7/16.
 */
//
//@Aspect
//@Component
public class LoginAop {
    public static final Logger logger = LoggerFactory.getLogger(LoginAop.class);

    @Autowired
    UserService userService;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) && execution(* com.aoineko.controller.*.*(..))")
    public void requestPointCut() {

    }

    @Around("requestPointCut()")
    public Object requestAround(ProceedingJoinPoint joinPoint) {

        logger.info("around requestPointcut");
        Object result = null;
        try {
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature)signature;

           Login login =  methodSignature.getMethod().getAnnotation(Login.class);
            if (login == null || login.value()) {
                authHeaderValid();
            }
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            if (throwable instanceof ServerException) {
                return new Response(((ServerException) throwable).getCode(), ((ServerException) throwable).getDesc());
            }
            logger.error(throwable.getMessage());
            return new Response(ExceptionConstants.Error.SERVER_ERROR.getStatus(), ExceptionConstants.Error.SERVER_ERROR.getDesc());
        }
        return result;
    }

    private void authHeaderValid() throws UserAuthException {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization) || authorization.indexOf("Bearer") != 0) {
            throw new UserAuthException();
        }
        String jwtString = authorization.split(" ")[1];
        if(!userService.authVerify(jwtString)) {
            throw new UserAuthException();
        }
    }


}
