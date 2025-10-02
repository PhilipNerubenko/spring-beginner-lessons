package com.philipnerubenko.spring.springboot.springboot_rest.controller;

import com.philipnerubenko.spring.springboot.springboot_rest.entity.Employee;
import com.philipnerubenko.spring.springboot.springboot_rest.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing employee data through HTTP API endpoints.
 * Handles CRUD operations for Employee entities with JSON-based communication.
 * All endpoints are prefixed with "/api".
 */
@RestController
@RequestMapping("/api")
public class MyRESTController {
  /**
   * Service component for business logic operations related to employees.
   * Automatically injected by Spring.
   */
  @Autowired private EmployeeService employeeService;

  /**
   * Retrieves all employee records from the database.
   * <p>
   * HTTP Method: GET
   * Endpoint: /api/employees
   *
   * @return List of all Employee objects in the database
   */
  @GetMapping("/employees")
  public List<Employee> showAllEmployees() {
    List<Employee> allEmployees = employeeService.getAllEmployees();

    return allEmployees;
  }

  /**
   * Retrieves a specific employee by ID.
   * <p>
   * HTTP Method: GET
   * Endpoint: /api/employees/{id}
   *
   * @param id the unique identifier of the employee to retrieve
   * @return the Employee object with the specified ID
   */
  @GetMapping("/employees/{id}")
  public Employee getEmployee(@PathVariable("id") int id) {
    Employee employee = employeeService.getEmployee(id);

    return employee;
  }

  /**
   * Adds a new employee to the database.
   * <p>
   * HTTP Method: POST
   * Endpoint: /api/employees
   *
   * @param employee the Employee object containing data to be added
   * @return the saved Employee object
   */
  @PostMapping("/employees")
  public Employee addNewEmployee(@RequestBody Employee employee) {
    employeeService.saveEmployee(employee);

    return employee;
  }

  /**
   * Updates an existing employee record in the database.
   * <p>
   * HTTP Method: PUT
   * Endpoint: /api/employees
   *
   * @param employee the Employee object containing updated data
   * @return the updated Employee object
   */
  @PutMapping("/employees")
  public Employee updateEmployee(@RequestBody Employee employee) {
    employeeService.saveEmployee(employee);

    return employee;
  }

  /**
   * Deletes an employee record from the database by ID.
   * <p>
   * HTTP Method: DELETE
   * Endpoint: /api/employees/{id}
   *
   * @param id the unique identifier of the employee to delete
   * @return confirmation message indicating successful deletion
   */
  @DeleteMapping("/employees/{id}")
  public String deleteEmployee(@PathVariable("id") int id) {
    Employee employee = employeeService.getEmployee(id);

    employeeService.deleteEmployee(id);

    return "Employee with ID = " + id + " was deleted";
  }
}
