package com.philip.spring.spring_course.spring_introduction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
// @ComponentScan("com.philip.spring.spring_course.spring_introduction")
@PropertySource("classpath:application.properties")
public class MyConfig {
   
   /**
    * Bean for the Cat class.
    * @return Cat object.
    */
   @Bean
   @Scope("singleton")
   public Pet catBean() {
       return new Cat();
   }

   /**
    * Bean for the Person class.
    * @return Person object.
    */
   @Bean
   public Person personBean() {
       return new Person(catBean());
   }
}
