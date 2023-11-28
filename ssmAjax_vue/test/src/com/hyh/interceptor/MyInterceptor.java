package com.hyh.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Aspect
@Component
public class MyInterceptor {

    @Around("this(com.hyh.controller.UploadController)") //* class的通配符
    public Object restrainBeforeUpload(ProceedingJoinPoint jp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        // Access session attributes or perform other actions
        System.out.println(session.getId());
        System.out.println(session.getAttribute("hello"));
        if (session.getAttribute("hello") != null)
            return jp.proceed();
        else
            return null;
    }

    @After("execution(* com.hyh.controller.UploadController.*(..))")
    public void afterUpload() {
        System.out.println("in");
    }


}
