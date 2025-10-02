package com.philip.spring.spring_course.spring_introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("personBean")
public class Person {
  // @Autowired
  // @Qualifier("catBean")
  /**
   * Pet of the person.
   */
  private Pet pet;
  //@Value("Philip") // this is a hardcoded value not recommended
  @Value("${person.surname}") // this is a value from properties file
  /**
   * Surname of the person.
   */
  private String surname;
  @Value("${person.age}")
  /**
   * Age of the person.
   */
  private int age;

  // @Autowired
  // public Person(@Qualifier("dog") Pet pet) {
  //     System.out.println("Person bean is created");
  //     this.pet = pet;
  // }

  /**
   * Constructor for the Person class.
   * @param pet Pet of the person.
   */
  public Person(Pet pet) {
    System.out.println("Person bean is created");
    this.pet = pet;
  }

  /**
   * Default constructor for the Person class.
   */
  public Person() {
    System.out.println("Person bean is created");
  }

  /**
   * Calls the pet of the person.
   */
  public void callYourPet() {
    System.out.println("Hello, my lovely pet!");
    pet.say();
  }

  // @Autowired
  // @Qualifier("dog")
  /**
   * Sets the pet of the person.
   * @param pet Pet of the person.
   */
  public void setPet(Pet pet) {
    System.out.println("Class Person: Set pet");
    this.pet = pet;
  }

  /**
   * Returns the surname of the person.
   * @return Surname of the person.
   */
  public String getSurname() {
    return surname;
  }

  /**
   * Sets the surname of the person.
   * @param surname Surname of the person.
   */
  public void setSurname(String surname) {
    System.out.println("Class Person: Set surname");
    this.surname = surname;
  }

  /**
   * Returns the age of the person.
   * @return Age of the person.
   */
  public int getAge() {
    return age;
  }

  /**
   * Sets the age of the person.
   * @param age Age of the person.
   */
  public void setAge(int age) {
    System.out.println("Class Person: Set age");
    this.age = age;
  }
}
