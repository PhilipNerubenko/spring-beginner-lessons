package com.philip.spring.spring_course.spring_introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringInitDestroyDemo {
  /**
   * Main method of the class.
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    /**
     * Create a new ClassPathXmlApplicationContext object.
     */
    ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext("applicationContext2.xml");
    /**
     * Get the Dog bean from the context.
     */
    Dog myDog = context.getBean("myPet", Dog.class);

    /**
     * Call the method say on the myDog object.
     */
    myDog.say();

    /**
     * Get the Dog bean from the context.
     */
    Dog yourDog = context.getBean("myPet", Dog.class);

    /**
     * Call the method say on the yourDog object.
     */
    yourDog.say();

    // with scope="prototype" the destroy method will not be called, you will have to implement the
    // closing logic yourself
    /**
     * Close the context.
     */
    context.close();
  }
}
