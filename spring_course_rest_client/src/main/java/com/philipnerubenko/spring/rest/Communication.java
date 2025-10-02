package com.philipnerubenko.spring.rest;

import com.philipnerubenko.spring.rest.entity.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Spring component for communication with a RESTful web service to manage employee data.
 * Uses RestTemplate to perform CRUD operations on employee resources through HTTP requests.
 */
@Component
public class Communication {
  /**
   * Autowired RestTemplate instance for making HTTP requests.
   */
  @Autowired private RestTemplate restTemplate;

  /**
   * Base URL for employee-related REST endpoints.
   */
  private final String URL = "http://localhost:8080/spring_course_rest/api/employees";

  /**
   * Retrieves all employee records from the REST API.
   * <p>
   * Sends an HTTP GET request to the base URL and returns a list of Employee objects.
   *
   * @return List of Employee objects retrieved from the API
   */
  public List<Employee> getAllEmployees() {
    ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(
        URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {});
    List<Employee> allEmployees = responseEntity.getBody();
    return allEmployees;
  }

  /**
   * Retrieves a specific employee by ID from the REST API.
   * <p>
   * Sends an HTTP GET request to the URL with the specified employee ID.
   *
   * @param id the unique identifier of the employee to retrieve
   * @return the Employee object with the specified ID
   */
  public Employee getEmployee(int id) {
    Employee employee = restTemplate.getForObject(URL + "/" + id, Employee.class);
    return employee;
  }

  /**
   * Saves or updates an employee record via the REST API.
   * <p>
   * If the employee ID is 0, sends an HTTP POST request to create a new employee.
   * Otherwise, sends an HTTP PUT request to update an existing employee.
   *
   * @param employee the Employee object to be saved or updated
   */
  public void saveEmployee(Employee employee) {
    int id = employee.getId();

    if (id == 0) {
      ResponseEntity<String> responseEntity =
          restTemplate.exchange(URL, HttpMethod.POST, new HttpEntity<>(employee), String.class);
      System.out.println("New Employee was added to DB");
      System.out.println(responseEntity.getBody());
    } else {
      restTemplate.put(URL, employee);
      System.out.println("Employee with ID " + id + " was updated");
    }
  }

  /**
   * Deletes an employee record from the REST API by ID.
   * <p>
   * Sends an HTTP DELETE request to the URL with the specified employee ID.
   *
   * @param id the unique identifier of the employee to delete
   */
  public void deleteEmployee(int id) {
    restTemplate.delete(URL + "/" + id);
    System.out.println("Employee with ID " + id + " was deleted from DB");
  }
}
