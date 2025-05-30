package com.philip.spring.spring_course.aop;

public class Student {
  /**
   * Name and surname of the student.
   */
  private String nameSurname;

  /**
   * Course of the student.
   */
  private int course;

  /**
   * Average grade of the student.
   */
  private double averageGrade;

  /**
   * Constructor for the Student class.
   * @param nameSurname Name and surname of the student.
   * @param course Course of the student.
   * @param averageGrade Average grade of the student.
   */
  public Student(String nameSurname, int course, double averageGrade) {
    this.nameSurname = nameSurname;
    this.course = course;
    this.averageGrade = averageGrade;
  }

  /**
   * Returns the name and surname of the student.
   * @return Name and surname of the student.
   */
  public String getNameSurname() {
    return nameSurname;
  }

  /**
   * Sets the name and surname of the student.
   * @param nameSurname Name and surname of the student.
   */
  public void setNameSurname(String nameSurname) {
    this.nameSurname = nameSurname;
  }

  /**
   * Returns the course of the student.
   * @return Course of the student.
   */
  public int getCourse() {
    return course;
  }

  /**
   * Sets the course of the student.
   * @param course Course of the student.
   */
  public void setCourse(int course) {
    this.course = course;
  }

  /**
   * Returns the average grade of the student.
   * @return Average grade of the student.
   */
  public double getAverageGrade() {
    return averageGrade;
  }

  /**
   * Sets the average grade of the student.
   * @param averageGrade Average grade of the student.
   */
  public void setAverageGrade(double averageGrade) {
    this.averageGrade = averageGrade;
  }

  /**
   * Returns a string representation of the student.
   * @return String representation of the student.
   */
  @Override
  public String toString() {
    return "Student [nameSurname=" + nameSurname + ", course=" + course
        + ", averageGrade=" + averageGrade + "]";
  }
}
