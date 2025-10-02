package com.philip.spring.spring_course.spring_introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfigWithAnnotations {
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
     * Get the Person bean from the context.
     */
    Person person = context.getBean("personBean", Person.class);
    /**
     * Call the method callYourPet on the person object.
     */
    person.callYourPet();

    // Cat cat = context.getBean("catBean", Cat.class);
    // cat.say();
    /**
     * Print the surname of the person.
     */
    System.out.println(person.getSurname());
    /**
     * Print the age of the person.
     */
    System.out.println(person.getAge());

    /**
     * Close the context.
     */
    context.close();
  }
}
