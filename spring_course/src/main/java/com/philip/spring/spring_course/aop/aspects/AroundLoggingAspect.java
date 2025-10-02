package com.philip.spring.spring_course.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AroundLoggingAspect {
  /**
   * Around advice for the returnBook method in UniLibrary.
   * @param proceedingJoinPoint ProceedingJoinPoint object.
   * @return Result of the target method.
   * @throws Throwable If an exception occurs.
   */
  @Around("execution(* com.philip.spring.spring_course.aop.UniLibrary.returnBook())")
  public Object aroundReturnBookLoggingAdvice(ProceedingJoinPoint proceedingJoinPoint)
      throws Throwable {
    System.out.println("aroundReturnBookLoggingAdvice: Logging the attempt to return a book");
    System.out.println("__________________________");

    long start = System.currentTimeMillis();
    Object targetMethodResult = null;

    try {
      targetMethodResult = proceedingJoinPoint.proceed();
    } catch (Throwable throwable) {
      System.out.println(
          "aroundReturnBookLoggingAdvice: An exception occurred while returning a book");
      System.out.println("Exception: " + throwable.getMessage());
      targetMethodResult = "Unknown Book"; // not recommended, but for demonstration purposes
      throw throwable; // rethrow the exception to maintain the original behavior, this recommended
    }
    targetMethodResult = "The Great Gatsby"; // not recommended, but for demonstration purposes

    long end = System.currentTimeMillis();

    System.out.println("aroundReturnBookLoggingAdvice: Logging the successful return of a book");
    System.out.println("Time taken to execute the method: " + (end - start) + " milliseconds");
    System.out.println("__________________________");

    return targetMethodResult;
  }
}
