package com.philip.spring.spring_course.aop;

import org.springframework.stereotype.Component;

@Component
public class SchoolLibrary extends AbstractLibrary {
  /**
   * Overrides the getBook method in AbstractLibrary.
   */
  @Override
  public void getBook() {
    System.out.println("We are getting a book from the School Library");
  }
}
