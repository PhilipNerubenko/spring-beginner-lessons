package com.philip.spring.spring_course.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
  /**
   * Before advice for the getBook method.
   */
  @Before("execution(public void getBook())")
  public void beforeGetBookAdvice() {
    System.out.println("Executing beforeGetBookAdvice");
  }

  /**
   * Before advice for all methods that start with "get".
   */
  @Before("execution(* get*())")
  public void beforeGetBookAndMagazineAdvice() {
    System.out.println("Executing beforeGetBookAndMagazineAdvice");
  }

  /**
   * Before advice for the getBook method with a parameter of type Book.
   */
  @Before("execution(public void "
      + "com.philip.spring.spring_course.aop.UniLibrary.getBookWithParameter(com.philip.spring."
      + "spring_course.aop.Book))")
  public void
  beforeGetBookWithParameterTypeBookAdvice() {
    System.out.println("Executing beforeGetBookWithParameterTypeBookAdvice");
  }

  /**
   * Before advice for all methods with any number of parameters.
   */
  @Before("execution(public void *(..))")
  public void beforeGetBookWithManyCountParameterAdvice() {
    System.out.println("Executing beforeGetBookWithManyCountParameterAdvice");
  }

  /**
   * Before advice for the returnBook method.
   */
  @Before("execution(* returnBook())")
  public void beforeReturnBookAdvice() {
    System.out.println("Executing beforeReturnBookAdvice");
  }
}
