package com.philip.spring.spring_course.aop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Book {
  /**
   * Name of the book.
   */
  @Value("The Great Gatsby") private String name;

  /**
   * Author of the book.
   */
  @Value("F. Scott Fitzgerald") private String author;

  /**
   * Year of publication of the book.
   */
  @Value("1925") private int yearOfPublication;

  /**
   * Returns the name of the book.
   * @return Name of the book.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the author of the book.
   * @return Author of the book.
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Returns the year of publication of the book.
   * @return Year of publication of the book.
   */
  public int getYearOfPublication() {
    return yearOfPublication;
  }
}
