package com.philip.spring.spring_course.spring_introduction;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton") // default scope is singleton
// @Scope("prototype") // if you want to use prototype scope, uncomment this line
public class Dog implements Pet {
  // private String name;

  /**
   * Constructor for the Dog class.
   */
  public Dog() {
    System.out.println("Dog bean is created");
  }

  /**
   * Prints "Bow-Wow".
   */
  @Override
  public void say() {
    System.out.println("Bow-Wow");
  }

  /**
   * Initialization method for the Dog class.
   */
  @PostConstruct
  protected void init() {
    System.out.println("Class Dog: init method");
  }

  /**
   * Destruction method for the Dog class.
   */
  @PreDestroy
  private void destroy() {
    System.out.println("Class Dog: destroy method");
  }

  // public String getName() {
  //     return name;
  // }

  // public void setName(String name) {
  //     this.name = name;
  // }
}
