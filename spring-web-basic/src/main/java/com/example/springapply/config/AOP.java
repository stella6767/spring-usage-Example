package com.example.springapply.config;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class AOP {


    @Around("bean(boardService)")
    public Object stopWatch(ProceedingJoinPoint joinPoint) throws Throwable {


        Object proceed = null;

        String methodName = joinPoint.getSignature().getName();


        long startTime = System.currentTimeMillis();
        proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("실행시간 " + totalTime);


        return proceed;
    }


}
