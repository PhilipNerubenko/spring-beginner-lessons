package com.philip.spring.spring_course.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAndSecurityAspectCombinationPointcut {
  /**
   * Pointcut for all get methods in UniLibrary.
   */
  @Pointcut("execution(* com.philip.spring.spring_course.aop.UniLibrary.get*())")
  private void allGetMethodsFromUniLibrary() {}

  /**
   * Pointcut for all return methods in UniLibrary.
   */
  @Pointcut("execution(* com.philip.spring.spring_course.aop.UniLibrary.return*())")
  private void allReturnMethodsFromUniLibrary() {}

  /**
   * Pointcut for all get and return methods in UniLibrary.
   */
  @Pointcut("allGetMethodsFromUniLibrary() || allReturnMethodsFromUniLibrary()")
  private void allGetAndReturnMethodsFromUniLibrary() {}

  /**
   * Pointcut for all methods in UniLibrary.
   */
  @Pointcut("execution (* com.philip.spring.spring_course.aop.UniLibrary.*(..))")
  private void allMethodsFromUniLibrary() {}

  /**
   * Pointcut for the returnMagazine method in UniLibrary.
   */
  @Pointcut("execution(* com.philip.spring.spring_course.aop.UniLibrary.returnMagazine())")
  private void returnMagazineFromUniLibrary() {}

  /**
   * Pointcut for all methods in UniLibrary except returnMagazine.
   */
  @Pointcut("allMethodsFromUniLibrary() && !returnMagazineFromUniLibrary()")
  private void allMethodsFromUniLibraryExceptReturnMagazine() {}

  /**
   * Before advice for all get methods in UniLibrary.
   */
  @Before("allGetMethodsFromUniLibrary()")
  public void beforeGetLoggingAdvice() {
    System.out.println("Executing beforeGetLoggingAdvice from UniLibrary");
  }

  /**
   * Before advice for all return methods in UniLibrary.
   */
  @Before("allReturnMethodsFromUniLibrary()")
  public void beforeReturnLoggingAdvice() {
    System.out.println("Executing beforeReturnLoggingAdvice from UniLibrary");
  }

  /**
   * Before advice for all get and return methods in UniLibrary.
   */
  @Before("allGetAndReturnMethodsFromUniLibrary()")
  public void beforeGetAndReturnLoggingAdvice() {
    System.out.println("Executing beforeGetAndReturnLoggingAdvice from UniLibrary");
  }

  /**
   * Before advice for all methods in UniLibrary except returnMagazine.
   */
  @Before("allMethodsFromUniLibraryExceptReturnMagazine()")
  public void beforeAllMethodsExceptReturnMagazine() {
    System.out.println("Executing beforeAllMethodsExceptReturnMagazine from UniLibrary");
  }
}
