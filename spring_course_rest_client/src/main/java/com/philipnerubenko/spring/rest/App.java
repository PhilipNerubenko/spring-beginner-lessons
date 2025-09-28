package com.philipnerubenko.spring.rest;

import com.philipnerubenko.spring.rest.configuration.MyConfig;
import com.philipnerubenko.spring.rest.entity.Employee;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(MyConfig.class);
        
        Communication communication = context.getBean("communication", Communication.class);
        
        List<Employee> allEmployees = communication.getAllEmployees();
        System.out.println(allEmployees);
        
        Employee empByID = communication.getEmployee(1);
        System.out.println(empByID);
        
        Employee emp = new Employee("Sveta", "Sokolova"
                , "IT", 1200);
        emp.setId(11);  // This id to your database
        communication.saveEmployee(emp);
        
        communication.deleteEmployee(11);
    }
}
