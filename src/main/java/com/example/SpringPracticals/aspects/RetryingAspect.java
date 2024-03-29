package com.example.SpringPracticals.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RetryingAspect {
  @Pointcut("@annotation(com.example.SpringPracticals.aspects.Retryable)")
  public void retryable() { }

  @Around("retryable()")
  public Object retryAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    int totalTries = 0;
    MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
    Retryable annotation = signature.getMethod()
        .getAnnotation(Retryable.class);
    int maxRetries = annotation.maxRetries();
    long delay = annotation.delay();
    Class<? extends Throwable>[] retryExceptions = annotation.retryExceptions();
    while (totalTries < maxRetries) {
      try {
        return proceedingJoinPoint.proceed();
      } catch (Throwable e) {
        if(!isRetryableException(e, retryExceptions)) {
          throw e;
        }
        totalTries++;
        try {
          Thread.sleep(delay);
        } catch (InterruptedException interruptedException) {
          Thread.currentThread().interrupt();
        }
      }
    }
    return proceedingJoinPoint.proceed();
  }

  private boolean isRetryableException(Throwable e, Class<? extends Throwable>[] retryExceptions) {
    for (Class<? extends Throwable> retryException : retryExceptions) {
      if(retryException.isInstance(e)) {
        return true;
      }
    }
    return false;
  }


}
