package com.philip.spring.spring_course.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test3 {
  /**
   * Main method of the class.
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    System.out.println("Method main starts");
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
     * Call the method returnBook on the uniLibrary object and save the result in bookName.
     */
    String bookName = uniLibrary.returnBook();
    System.out.println("Book returned: " + bookName);

    /**
     * Close the context.
     */
    context.close();
    System.out.println("Method main ends");
  }
}
