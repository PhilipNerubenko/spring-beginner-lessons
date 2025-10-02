package com.philipnerubenko.spring.mvc_hibernate_aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * A Spring AOP aspect component that logs the execution of all methods in the
 * repository layer (DAO package). This aspect intercepts method calls in the
 * specified package and prints messages before and after method execution.
 */
@Component
@Aspect
public class MyLoggingAspect {
  /**
   * Around advice that logs the start and end of every method execution in the
   * `com.philipnerubenko.spring.mvc_hibernate_aop.dao` package.
   * <p>
   * This advice:
   * 1. Extracts the method name from the join point.
   * 2. Prints a "Begin" message before method execution.
   * 3. Executes the target method using `proceed()`.
   * 4. Prints an "End" message after method execution.
   * 5. Returns the result of the method execution.
   *
   * @param proceedingJoinPoint the join point providing access to the method context
   * @return the result of the intercepted method execution
   * @throws Throwable if an exception occurs during method execution or processing
   */
  @Around("execution(* com.philipnerubenko.spring.mvc_hibernate_aop.dao.*.*(..))")
  public Object aroundAllRepositoryMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint)
      throws Throwable {
    MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
    String methodName = methodSignature.getName();

    System.out.println("Begin of " + methodName);
    Object targetMethodResult = proceedingJoinPoint.proceed();
    System.out.println("End of " + methodName);

    return targetMethodResult;
  }
}
