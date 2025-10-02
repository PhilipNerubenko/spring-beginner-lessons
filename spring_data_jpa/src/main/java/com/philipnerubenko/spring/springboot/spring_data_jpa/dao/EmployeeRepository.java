package com.philipnerubenko.spring.springboot.spring_data_jpa.dao;

import com.philipnerubenko.spring.springboot.spring_data_jpa.entity.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing employee data access operations.
 * Extends Spring Data JPA's {@link JpaRepository} to provide CRUD functionality
 * and database query capabilities for Employee entities.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  /**
   * Retrieves all employees with the specified name.
   *
   * @param name the name to search for in employee records
   * @return List of Employee objects matching the given name
   */
  public List<Employee> findAllByName(String name);
}
