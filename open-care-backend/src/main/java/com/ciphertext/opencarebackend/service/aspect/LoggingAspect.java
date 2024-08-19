package com.ciphertext.opencarebackend.service.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.ciphertext.opencarebackend.service.*.*(..))")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Entering method: {}", methodName);
    }

    @AfterReturning(value = "execution(* com.ciphertext.opencarebackend.service.*.*(..))", returning = "o")
    public void afterMethodExecution(JoinPoint joinPoint, Object o) {
        log.info("after execution from  return value {}", o);
    }

    @AfterThrowing(value = "execution(* com.ciphertext.opencarebackend.service.*.*(..))", throwing = "ex")
    public void afterThrowingError(Exception ex) {
        log.error(String.valueOf(ex));
    }
}
