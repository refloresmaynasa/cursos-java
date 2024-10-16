package me.rflores.clienteapp.services;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut that matches all repository methods
    @Before("execution(* com.example.demo.repository.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Before method: " + joinPoint.getSignature().getName());
    }

    // Logging successful execution of repository methods
    @AfterReturning(pointcut = "execution(* me.rflores.clienteapp.models.daos.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method returned: " + joinPoint.getSignature().getName() + " Result: " + result);
    }

    // Logging exceptions thrown by repository methods
    @AfterThrowing(pointcut = "execution(* me.rflores.clienteapp.models.daos.*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in method: " + joinPoint.getSignature().getName() + " Error: " + error);
    }
}

