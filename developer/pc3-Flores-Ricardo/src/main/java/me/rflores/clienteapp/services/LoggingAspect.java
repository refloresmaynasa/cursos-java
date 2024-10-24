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

    @Before("execution(* me.rflores.clienteapp.models.daos.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Antes del metodo: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* me.rflores.clienteapp.models.daos.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Resultado Metodo: " + joinPoint.getSignature().getName() + " Result: " + result);
    }

    @AfterThrowing(pointcut = "execution(* me.rflores.clienteapp.models.daos.*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception en el metodo: " + joinPoint.getSignature().getName() + " Error: " + error);
    }
}

