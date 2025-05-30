package com.philip.spring.spring_course.aop;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class University {
  /**
   * List of students.
   */
  private List<Student> students = new ArrayList<>();

  /**
   * Adds students to the list.
   */
  public void addStudent() {
    Student student = new Student("John Doe", 1, 4.5);
    Student student2 = new Student("Jane Smith", 2, 3.8);
    Student student3 = new Student("Alice Johnson", 3, 4.0);
    students.add(student);
    students.add(student2);
    students.add(student3);
  }

  /**
   * Returns the list of students.
   * @return List of students.
   */
  public List<Student> getStudents() {
    System.out.println("Starts working with getStudents method");
    System.out.println(students.get(3));
    System.out.println("University: Getting list of students");
    System.out.println(students);
    return students;
  }
}
