package com.philipnerubenko.spring.springboot.spring_data_jpa.service;

import com.philipnerubenko.spring.springboot.spring_data_jpa.dao.EmployeeRepository;
import com.philipnerubenko.spring.springboot.spring_data_jpa.entity.Employee;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation class for managing employee business logic.
 * Provides CRUD operations and custom query functionality for Employee entities
 * through the EmployeeRepository interface.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
  /**
   * Repository interface for database operations related to Employee entities.
   * Automatically injected by Spring.
   */
  @Autowired private EmployeeRepository employeeRepository;

  /**
   * Retrieves all employee records from the database.
   *
   * @return a List of all Employee objects in the database
   */
  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  /**
   * Persists or updates an employee record in the database.
   * <p>
   * If the employee ID is 0, a new record is created.
   * If the employee ID is non-zero, an existing record is updated.
   *
   * @param employee the Employee object to be saved or updated
   */
  @Override
  public void saveEmployee(Employee employee) {
    employeeRepository.save(employee);
  }

  /**
   * Retrieves an employee record by its unique identifier (ID).
   * <p>
   * Returns null if no employee is found with the specified ID.
   *
   * @param id the unique identifier of the Employee to retrieve
   * @return the Employee object with the specified ID, or null if not found
   */
  @Override
  public Employee getEmployee(int id) {
    Employee employee = null;
    Optional<Employee> optional = employeeRepository.findById(id);
    if (optional.isPresent()) {
      employee = optional.get();
    }
    return employee;
  }

  /**
   * Deletes an employee record from the database by its unique identifier (ID).
   *
   * @param id the unique identifier of the Employee to delete
   */
  @Override
  public void deleteEmployee(int id) {
    employeeRepository.deleteById(id);
  }

  /**
   * Retrieves all employees with the specified name.
   *
   * @param name the name to search for in employee records
   * @return List of Employee objects matching the given name
   */
  @Override
  public List<Employee> findAllByName(String name) {
    List<Employee> employees = employeeRepository.findAllByName(name);
    return employees;
  }
}
