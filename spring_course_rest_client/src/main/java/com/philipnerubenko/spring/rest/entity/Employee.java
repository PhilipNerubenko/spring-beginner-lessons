package com.philipnerubenko.spring.rest.entity;

/**
 * Represents an employee with basic information including ID, name, surname,
 * department, and salary. This class serves as a data model for employee-related operations.
 */
public class Employee {
  /**
   * Unique identifier for the employee.
   */
  private int id;

  /**
   * First name of the employee.
   */
  private String name;

  /**
   * Last name of the employee.
   */
  private String surname;

  /**
   * Department where the employee works.
   */
  private String department;

  /**
   * Salary of the employee.
   */
  private int salary;

  /**
   * Default constructor required for framework compatibility (e.g., deserialization).
   * Initializes an empty Employee object.
   */
  public Employee() {}

  /**
   * Constructs a new Employee with specified attributes.
   *
   * @param name       the employee's first name
   * @param surname    the employee's last name
   * @param department the employee's department
   * @param salary     the employee's salary
   */
  public Employee(String name, String surname, String department, int salary) {
    this.name = name;
    this.surname = surname;
    this.department = department;
    this.salary = salary;
  }

  /**
   * Returns the unique identifier of the employee.
   *
   * @return the employee ID
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the unique identifier of the employee.
   *
   * @param id the new ID value
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Returns the employee's first name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the employee's first name.
   *
   * @param name the new name value
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the employee's last name.
   *
   * @return the surname
   */
  public String getSurname() {
    return surname;
  }

  /**
   * Sets the employee's last name.
   *
   * @param surname the new surname value
   */
  public void setSurname(String surname) {
    this.surname = surname;
  }

  /**
   * Returns the employee's department.
   *
   * @return the department
   */
  public String getDepartment() {
    return department;
  }

  /**
   * Sets the employee's department.
   *
   * @param department the new department value
   */
  public void setDepartment(String department) {
    this.department = department;
  }

  /**
   * Returns the employee's salary.
   *
   * @return the salary
   */
  public int getSalary() {
    return salary;
  }

  /**
   * Sets the employee's salary.
   *
   * @param salary the new salary value
   */
  public void setSalary(int salary) {
    this.salary = salary;
  }

  /**
   * Returns a string representation of the employee, including all attributes.
   *
   * @return a formatted string with employee details
   */
  @Override
  public String toString() {
    return "Employee{"
        + "id=" + id + ", name=" + name + ", surname=" + surname + ", department=" + department
        + ", salary=" + salary + '}';
  }
}
