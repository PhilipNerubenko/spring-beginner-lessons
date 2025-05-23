package com.philip.spring.spring_course.spring_introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanScopeDemo {
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
       
       // // Dog myDog = context.getBean("myPet", Dog.class);
       // // myDog.setName("Rex");
       // // Dog yourDog = context.getBean("myPet", Dog.class);
       // // yourDog.setName("Bobik");

       // // System.out.println("\nMy dog's name is: " + myDog.getName());
       // // System.out.println("Your dog's name is: " + yourDog.getName());
       // System.out.println("\nmyDog == yourDog: " + (myDog == yourDog));
       // System.out.println("myDog: " + myDog);
       // System.out.println("yourDog: " + yourDog);

       /**
        * Close the context.
        */
       context.close();
   }
}
