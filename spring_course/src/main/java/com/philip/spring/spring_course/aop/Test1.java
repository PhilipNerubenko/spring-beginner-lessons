package com.philip.spring.spring_course.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test1 {
  /**
   * Main method of the class.
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    /**
     * Create a new AnnotationConfigApplicationContext object.
     */
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(MyConfig.class);

    /**
     * Get the UniLibrary bean from the context.
     */
    UniLibrary uniLibrary = context.getBean("uniLibrary", UniLibrary.class);

    /**
     * Get the Book bean from the context.
     */
    Book book = context.getBean("book", Book.class);

    /**
     * Call the method getBook on the uniLibrary object.
     */
    uniLibrary.getBook();
    System.out.println();

    /**
     * Call the method getBookWithParameter on the uniLibrary object with the book object as an
     * argument.
     */
    uniLibrary.getBookWithParameter(book);
    System.out.println();

    /**
     * Call the method returnBook on the uniLibrary object.
     */
    uniLibrary.returnBook();
    System.out.println();

    /**
     * Call the method getMagazine on the uniLibrary object.
     */
    uniLibrary.getMagazine();
    System.out.println();

    /**
     * Call the method returnMagazine on the uniLibrary object.
     */
    uniLibrary.returnMagazine();
    System.out.println();

    /**
     * Call the method addBook on the uniLibrary object with the arguments "Philip" and book.
     */
    uniLibrary.addBook("Philip", book);
    System.out.println();

    /**
     * Call the method addMagazine on the uniLibrary object.
     */
    uniLibrary.addMagazine();
    System.out.println();

    /**
     * Get the SchoolLibrary bean from the context.
     */
    SchoolLibrary schoolLibrary = context.getBean("schoolLibrary", SchoolLibrary.class);
    /**
     * Call the method getBook on the schoolLibrary object.
     */
    schoolLibrary.getBook();
    System.out.println();

    /**
     * Close the context.
     */
    context.close();
  }
}
