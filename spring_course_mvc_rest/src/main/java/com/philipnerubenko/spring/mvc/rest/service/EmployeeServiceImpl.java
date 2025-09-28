package com.philipnerubenko.spring.mvc.rest.service;

import com.philipnerubenko.spring.mvc.rest.dao.EmployeeDAO;
import com.philipnerubenko.spring.mvc.rest.entity.Employee;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired private EmployeeDAO employeeDAO;

  @Override
  @Transactional
  public List<Employee> getAllEmployees() {
    return employeeDAO.getAllEmployees();
  }

  @Override
  @Transactional
  public void saveEmployee(Employee employee) {
    employeeDAO.saveEmployee(employee);
  }

  @Override
  @Transactional
  public Employee getEmployee(int id) {
    return employeeDAO.getEmployee(id);
  }

  @Override
  @Transactional
  public void deleteEmployee(int id) {
    employeeDAO.deleteEmployee(id);
  }
}
