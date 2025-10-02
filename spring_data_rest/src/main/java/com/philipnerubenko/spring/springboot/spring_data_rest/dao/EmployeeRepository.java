package com.philipnerubenko.spring.springboot.spring_data_rest.dao;

import com.philipnerubenko.spring.springboot.spring_data_rest.entity.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing employee data access operations.
 * Extends Spring Data JPA's {@link JpaRepository} to provide CRUD functionality
 * and database query capabilities for Employee entities.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}
