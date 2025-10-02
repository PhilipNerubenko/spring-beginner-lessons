package com.philip.spring.spring_course.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(20)
public class SecurityAspectOrderExecutingAndJoinPoint {
  /**
   * Before advice for all methods that start with "add".
   */
  @Before("com.philip.spring.spring_course.aop.aspects.MyPointcuts.allAddMethods()")
  public void beforeAddSecurityAdvice() {
    System.out.println("Executing beforeAddSecurityAdvice");
    System.out.println("__________________________");
  }
}
