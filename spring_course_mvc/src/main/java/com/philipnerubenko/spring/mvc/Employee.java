package com.philipnerubenko.spring.mvc;

import com.philipnerubenko.spring.mvc.validation.CheckEmail;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an employee with various attributes including name, surname, salary,
 * department, car brand, languages, phone number, and email. This class includes
 * validation constraints using Bean Validation annotations to enforce data integrity.
 */
public class Employee {
  /**
   * The employee's name, which must be at least 2 characters long.
   * <p>
   * Annotation: {@literal @}Size(min = 2, message = "name must be min 2 symbols")
   */
  @Size(min = 2, message = "name must be min 2 symbols") private String name;

  /**
   * The employee's surname, which must not be blank (i.e., cannot be null, empty,
   * or whitespace only).
   * <p>
   * Annotation: {@literal @}NotBlank(message = "surname is required field")
   */
  // @NotNull(message = "surname is required field")
  // @NotEmpty(message = "surname is required field")
  @NotBlank(message = "surname is required field") private String surname;

  /**
   * The employee's salary, which must be between 500 and 1000 inclusive.
   * <p>
   * Annotations:
   * <ul>
   *   <li>{@literal @}Min(value = 500, message = "must be greater than 499")</li>
   *   <li>{@literal @}Max(value = 1000, message = "must be less than 1001")</li>
   * </ul>
   */
  @Min(value = 500, message = "must be greater than 499")
  @Max(value = 1000, message = "must be less than 1001")
  private int salary;

  /** The department the employee belongs to. No validation constraints applied. */
  private String department;

  /** A map of department codes to full names (e.g., "IT" -> "Information Technology"). */
  private Map<String, String> departments;

  /** The brand of the employee's car. No validation constraints applied. */
  private String carBrand;

  /** A map of car brand abbreviations to full names (e.g., "BMV" -> "BMV"). */
  private Map<String, String> carBrands;

  /** An array of programming languages the employee knows. No validation constraints applied. */
  private String[] languages;

  /** A map of language names to their ISO codes (e.g., "English" -> "EN"). */
  private Map<String, String> languageList;

  /**
   * The employee's phone number, which must follow the pattern XXX-XX-XX (three digits,
   * hyphen, two digits, hyphen, two digits).
   * <p>
   * Annotation: {@literal @}Pattern(regexp = "\\d{3}-\\d{2}-\\d{2}", message = "please use pattern
   * XXX-XX-XX")
   */
  @Pattern(regexp = "\\d{3}-\\d{2}-\\d{2}", message = "please use pattern XXX-XX-XX")
  private String phoneNumber;

  /**
   * The employee's email address, which must end with "abc.com".
   * <p>
   * Annotation: {@literal @}CheckEmail(value = "abc.com", message = "email must ends with abc.com")
   */
  @CheckEmail(value = "abc.com", message = "email must ends with abc.com") private String email;

  /**
   * Default constructor that initializes static data structures for departments,
   * car brands, and languages.
   */
  public Employee() {
    departments = new HashMap<>();
    departments.put("IT", "Information Technology");
    departments.put("HR", "Human Resources");
    departments.put("Sales", "Sales");

    carBrands = new HashMap<>();
    carBrands.put("BMV", "BMV");
    carBrands.put("Audi", "Audi");
    carBrands.put("Mercedes-Benz", "MB");
    languageList = new HashMap<>();
    languageList.put("English", "EN");
    languageList.put("Deutch", "DE");
    languageList.put("French", "FR");
  }

  /**
   * Returns the employee's name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the employee's name.
   *
   * @param name the new name value
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the employee's surname.
   *
   * @return the surname
   */
  public String getSurname() {
    return surname;
  }

  /**
   * Sets the employee's surname.
   *
   * @param surname the new surname value
   */
  public void setSurname(String surname) {
    this.surname = surname;
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
   * Returns the map of department codes to full names.
   *
   * @return the departments map
   */
  public Map<String, String> getDepartments() {
    return departments;
  }

  /**
   * Sets the map of department codes to full names.
   *
   * @param departments the new departments map
   */
  public void setDepartments(Map<String, String> departments) {
    this.departments = departments;
  }

  /**
   * Returns the employee's car brand.
   *
   * @return the car brand
   */
  public String getCarBrand() {
    return carBrand;
  }

  /**
   * Sets the employee's car brand.
   *
   * @param carBrand the new car brand value
   */
  public void setCarBrand(String carBrand) {
    this.carBrand = carBrand;
  }

  /**
   * Returns the map of car brand abbreviations to full names.
   *
   * @return the carBrands map
   */
  public Map<String, String> getCarBrands() {
    return carBrands;
  }

  /**
   * Sets the map of car brand abbreviations to full names.
   *
   * @param carBrands the new carBrands map
   */
  public void setCarBrands(Map<String, String> carBrands) {
    this.carBrands = carBrands;
  }

  /**
   * Returns the array of programming languages the employee knows.
   *
   * @return the languages array
   */
  public String[] getLanguages() {
    return languages;
  }

  /**
   * Sets the array of programming languages the employee knows.
   *
   * @param languages the new languages array
   */
  public void setLanguages(String[] languages) {
    this.languages = languages;
  }

  /**
   * Returns the map of language names to their ISO codes.
   *
   * @return the languageList map
   */
  public Map<String, String> getLanguageList() {
    return languageList;
  }

  /**
   * Sets the map of language names to their ISO codes.
   *
   * @param languageList the new languageList map
   */
  public void setLanguageList(Map<String, String> languageList) {
    this.languageList = languageList;
  }

  /**
   * Returns the employee's phone number.
   *
   * @return the phone number
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Sets the employee's phone number.
   *
   * @param phoneNumber the new phone number value
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Returns the employee's email address.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the employee's email address.
   *
   * @param email the new email value
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Returns a string representation of the employee, including name, surname,
   * salary, and department.
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "Employee{"
        + "name=" + name + ", surname=" + surname + ", salary=" + salary
        + ", department=" + department + '}';
  }
}
