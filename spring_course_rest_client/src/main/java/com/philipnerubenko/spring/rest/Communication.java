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

@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/spring_course_rest/api/employees";
    
    public List<Employee> getAllEmployees() {
        
        ResponseEntity<List<Employee>> responseEntity = 
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {});
        List<Employee> allEmployees = responseEntity.getBody();
        return allEmployees;
    }
    
    public Employee getEmployee(int id) {
        Employee employee = restTemplate.getForObject(URL + "/" + id, Employee.class);
        return employee;
    }
    
    public void saveEmployee(Employee employee) {
        int id = employee.getId();
        
        if (id == 0) {
           ResponseEntity<String> responseEntity =
        restTemplate.exchange(URL, HttpMethod.POST,
                              new HttpEntity<>(employee),
                              String.class);
            System.out.println("New Employee was added to DB");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(URL, employee);
            System.out.println("Employee with ID " + id + " was updated");
        }
    }
    
    public void deleteEmployee(int id) {
        restTemplate.delete(URL + "/" + id);
        System.out.println("Employee with ID " + id + " was deleted from DB");
    }
}
