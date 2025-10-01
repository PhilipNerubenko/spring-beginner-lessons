package com.philipnerubenko.spring.springboot.spring_data_jpa.dao;

import com.philipnerubenko.spring.springboot.spring_data_jpa.entity.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public List<Employee> findAllByName(String name);
}
