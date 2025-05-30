package com.philip.spring.spring_course.spring_introduction;

import org.springframework.stereotype.Component;

@Component("catBean")
public class Cat implements Pet {
  /**
   * Constructor for the Cat class.
   */
  public Cat() {
    System.out.println("Cat bean is created");
  }

  /**
   * Prints "Meow".
   */
  @Override
  public void say() {
    System.out.println("Meow");
  }
}
