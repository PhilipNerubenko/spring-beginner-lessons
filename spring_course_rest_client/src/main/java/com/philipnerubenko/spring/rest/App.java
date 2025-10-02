package com.philipnerubenko.spring.rest;

import com.philipnerubenko.spring.rest.configuration.MyConfig;
import com.philipnerubenko.spring.rest.entity.Employee;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main application class that demonstrates integration with a RESTful service
 * for employee data operations. Uses Spring configuration to manage dependencies
 * and perform CRUD operations on Employee objects.
 */
public class App {
  /**
   * Entry point of the application. Demonstrates:
   * <ul>
   *   <li>Retrieving all employees</li>
   *   <li>Fetching an employee by ID</li>
   *   <li>Saving a new employee</li>
   *   <li>Deleting an existing employee</li>
   * </ul>
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    // Initialize Spring application context with configuration
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(MyConfig.class);

    // Retrieve Communication service bean for API interaction
    Communication communication = context.getBean("communication", Communication.class);

    // Get and print all employees from the database
    List<Employee> allEmployees = communication.getAllEmployees();
    System.out.println(allEmployees);

    // Get and print employee with ID=1
    Employee empByID = communication.getEmployee(1);
    System.out.println(empByID);

    // Create and save a new employee (assumes ID 11 exists in database)
    Employee emp = new Employee("Sveta", "Sokolova", "IT", 1200);
    emp.setId(11); // This id to your database
    communication.saveEmployee(emp);

    // Delete employee with ID=11
    communication.deleteEmployee(11);
  }
}
