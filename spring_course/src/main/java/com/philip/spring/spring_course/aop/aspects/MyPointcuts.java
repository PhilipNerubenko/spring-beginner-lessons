package com.philip.spring.spring_course.aop.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointcuts {
  /**
   * Pointcut for all methods that start with "add".
   */
  @Pointcut("execution(* add*(..))")
  public void allAddMethods() {}
}
