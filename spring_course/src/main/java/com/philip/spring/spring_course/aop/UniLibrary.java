package com.philip.spring.spring_course.aop;

import org.springframework.stereotype.Component;

@Component
public class UniLibrary extends AbstractLibrary {
  /**
   * Overrides the getBook method in AbstractLibrary.
   */
  @Override
  public void getBook() {
    System.out.println("We are getting a book from the University Library");
    System.out.println("__________________________");
  }

  /**
   * Gets a book from the University Library with a parameter.
   * @param book Book object.
   */
  public void getBookWithParameter(Book book) {
    System.out.println("We are getting a book from the University Library " + book.getName());
    System.out.println("___________________________");
  }

  /**
   * Returns a book to the University Library.
   * @return Name of the book.
   */
  public String returnBook() {
    int a = 10 / 0;
    System.out.println("We are returning a book to the University Library");
    return "War and Peace";
  }

  /**
   * Gets a magazine from the University Library.
   */
  public void getMagazine() {
    System.out.println("We are getting a magazine from the University Library");
    System.out.println("__________________________");
  }

  /**
   * Returns a magazine to the University Library.
   */
  public void returnMagazine() {
    System.out.println("We are returning a magazine to the University Library");
    System.out.println("__________________________");
  }

  /**
   * Adds a book to the University Library.
   * @param personName Name of the person.
   * @param book Book object.
   */
  public void addBook(String personName, Book book) {
    System.out.println("We are adding a book to the University Library");
    System.out.println("__________________________");
  }

  /**
   * Adds a magazine to the University Library.
   */
  public void addMagazine() {
    System.out.println("We are adding a magazine to the University Library");
    System.out.println("__________________________");
  }
}
