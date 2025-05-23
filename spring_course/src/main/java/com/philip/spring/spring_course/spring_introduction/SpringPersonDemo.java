package com.philip.spring.spring_course.spring_introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringPersonDemo {
   /**
    * Main method of the class.
    * @param args Command line arguments.
    */
   public static void main(String[] args) {
       // Pet pet = new Dog();
       /**
        * Create a new ClassPathXmlApplicationContext object.
        */
       ClassPathXmlApplicationContext context =
               new ClassPathXmlApplicationContext("applicationContext.xml");

       // Pet pet = context.getBean("myPet", Pet.class);

       // Person person = new Person(pet);
       /**
        * Get the Person bean from the context.
        */
       Person person = context.getBean("myPerson", Person.class);
       /**
        * Call the method callYourPet on the person object.
        */
       person.callYourPet();
       
       /**
        * Print the surname of the person.
        */
       System.out.println("Surname: " + person.getSurname());
       /**
        * Print the age of the person.
        */
       System.out.println("Age: " + person.getAge());

       /**
        * Close the context.
        */
       context.close();
   }
}
