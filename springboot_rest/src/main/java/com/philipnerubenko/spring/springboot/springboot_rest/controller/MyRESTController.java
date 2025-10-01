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

@RestController
@RequestMapping("/api")
public class MyRESTController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        
        return allEmployees;
    }
    
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        Employee employee = employeeService.getEmployee(id);
             
        return employee;
    }
   
    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
       employeeService.saveEmployee(employee);
        
        return employee;
    }
    
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
       employeeService.saveEmployee(employee);
        
        return employee;
    }
    
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        Employee employee = employeeService.getEmployee(id);
        
        employeeService.deleteEmployee(id);
        
        return "Employee with ID = " + id + " was deleted";
    }
}