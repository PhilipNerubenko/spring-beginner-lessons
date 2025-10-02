package com.philip.spring.spring_course.spring_introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringPetDemo {
  /**
   * Main method of the class.
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    /**
     * Create a new ClassPathXmlApplicationContext object.
     */
    ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext("applicationContext.xml");
    /**
     * Get the Pet bean from the context.
     */
    Pet pet = context.getBean("myPet", Pet.class);
    /**
     * Call the method say on the pet object.
     */
    pet.say();

    /**
     * Close the context.
     */
    context.close();
  }
}
