package com.philipnerubenko.spring.springboot.spring_data_rest.dao;

import com.philipnerubenko.spring.springboot.spring_data_rest.entity.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
