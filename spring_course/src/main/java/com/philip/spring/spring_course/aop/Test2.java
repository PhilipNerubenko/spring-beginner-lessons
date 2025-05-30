package com.philip.spring.spring_course.aop;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test2 {
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
     * Get the University bean from the context.
     */
    University university = context.getBean("university", University.class);
    /**
     * Call the method addStudent on the university object.
     */
    university.addStudent();
    try {
      /**
       * Get the list of students from the university object.
       */
      List<Student> students = university.getStudents();
      System.out.println("Students: " + students);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Caught an exception: " + e.getMessage());
    }

    /**
     * Close the context.
     */
    context.close();
  }
}
