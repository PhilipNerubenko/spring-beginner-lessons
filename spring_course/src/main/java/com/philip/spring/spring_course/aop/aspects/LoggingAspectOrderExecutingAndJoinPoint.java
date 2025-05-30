package com.philip.spring.spring_course.aop.aspects;

import com.philip.spring.spring_course.aop.Book;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(10)
public class LoggingAspectOrderExecutingAndJoinPoint {
  /**
   * Before advice for all methods that start with "add".
   * @param joinPoint JoinPoint object.
   */
  @Before("com.philip.spring.spring_course.aop.aspects.MyPointcuts.allAddMethods()")
  public void beforeAddLoggingAdvice(JoinPoint joinPoint) {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    System.out.println("methodSignature = " + methodSignature);
    System.out.println("methodSignature.getMethod() = " + methodSignature.getMethod());
    System.out.println("methodSignature.getReturnType() = " + methodSignature.getReturnType());
    System.out.println("methodSignature.getName() = " + methodSignature.getName());

    if (methodSignature.getName().equals("addBook")) {
      Object[] args = joinPoint.getArgs();
      for (Object arg : args) {
        if (arg instanceof Book) {
          Book book = (Book) arg;
          System.out.println("Book information: name - " + book.getName() + ", author - "
              + book.getAuthor() + ", year - " + book.getYearOfPublication());
        } else if (arg instanceof String) {
          System.out.println("Book add: " + arg);
        }
      }
    }

    System.out.println("Executing beforeAddLoggingAdvice");
    System.out.println("__________________________");
  }
}
