package com.philip.spring.spring_course.spring_introduction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringJavaConfigDemo {
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
     * Get the Cat bean from the context.
     */
    Pet cat1 = context.getBean("catBean", Pet.class);
    /**
     * Get the Cat bean from the context.
     */
    Pet cat2 = context.getBean("catBean", Pet.class);
    // cat.say();
    /**
     * Get the Person bean from the context.
     */
    Person person1 = context.getBean("personBean", Person.class);
    // Person person2 = context.getBean("personBean", Person.class);
    // System.out.println();
    // person1.callYourPet();
    // System.out.println();
    // person2.callYourPet();
    /**
     * Print the surname of the person.
     */
    System.out.println(person1.getSurname());
    /**
     * Print the age of the person.
     */
    System.out.println(person1.getAge());

    /**
     * Close the context.
     */
    context.close();
  }
}
