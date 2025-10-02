package com.philip.spring.spring_course.spring_introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeTest {
  /**
   * Main method of the class.
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    /**
     * Create a new ClassPathXmlApplicationContext object.
     */
    ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext("applicationContext3.xml");

    /**
     * Get the Dog bean from the context.
     */
    Dog myDog = context.getBean("dog", Dog.class);
    /**
     * Get the Dog bean from the context.
     */
    Dog yourDog = context.getBean("dog", Dog.class);
    /**
     * Print whether myDog and yourDog are the same object.
     */
    System.out.println("\nmyDog == yourDog: " + (myDog == yourDog));
    System.out.println("myDog: " + myDog);
    System.out.println("yourDog: " + yourDog);

    /**
     * Close the context.
     */
    context.close();
  }
}
