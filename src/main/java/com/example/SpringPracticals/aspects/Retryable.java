package com.example.SpringPracticals.aspects;

import java.lang.annotation.Retention;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Retryable {
  int maxRetries() default 3;
  long delay() default 1000;
  Class<? extends Throwable>[] retryExceptions() default {};
}
