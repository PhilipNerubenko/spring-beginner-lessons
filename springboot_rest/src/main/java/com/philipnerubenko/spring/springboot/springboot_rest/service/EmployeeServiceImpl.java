package com.philipnerubenko.spring.springboot.springboot_rest.service;

import com.philipnerubenko.spring.springboot.springboot_rest.dao.EmployeeDAO;
import com.philipnerubenko.spring.springboot.springboot_rest.entity.Employee;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation class for managing employee business logic.
 * Provides transactional operations for retrieving, saving, updating,
 * and deleting employee records through the EmployeeDAO.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
  /**
   * Data Access Object for interacting with the employee database.
   * Automatically injected by Spring.
   */
  @Autowired private EmployeeDAO employeeDAO;

  /**
   * Retrieves all employee records from the database.
   * <p>
   * This method is transactional, ensuring consistent data access.
   *
   * @return a List of all Employee objects in the database
   */
  @Override
  @Transactional
  public List<Employee> getAllEmployees() {
    return employeeDAO.getAllEmployees();
  }

  /**
   * Persists or updates an employee record in the database.
   * <p>
   * This method is transactional, ensuring atomic save operations.
   *
   * @param employee the Employee object to be saved or updated
   */
  @Override
  @Transactional
  public void saveEmployee(Employee employee) {
    employeeDAO.saveEmployee(employee);
  }

  /**
   * Retrieves an employee record by its unique identifier (ID).
   * <p>
   * This method is transactional, ensuring consistent data retrieval.
   *
   * @param id the unique identifier of the Employee to retrieve
   * @return the Employee object with the specified ID, or null if not found
   */
  @Override
  @Transactional
  public Employee getEmployee(int id) {
    return employeeDAO.getEmployee(id);
  }

  /**
   * Deletes an employee record from the database by its unique identifier (ID).
   * <p>
   * This method is transactional, ensuring atomic deletion operations.
   *
   * @param id the unique identifier of the Employee to delete
   */
  @Override
  @Transactional
  public void deleteEmployee(int id) {
    employeeDAO.deleteEmployee(id);
  }
}
