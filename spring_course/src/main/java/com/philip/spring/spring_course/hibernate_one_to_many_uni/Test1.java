package com.philip.spring.spring_course.hibernate_one_to_many_uni;

import com.philip.spring.spring_course.hibernate_one_to_many_uni.entity.Department;
import com.philip.spring.spring_course.hibernate_one_to_many_uni.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1 {
  public static void main(String[] args) {
    try (SessionFactory factory = new Configuration()
                                      .configure("hibernate.cfg.xml")
                                      .addAnnotatedClass(Employee.class)
                                      .addAnnotatedClass(Department.class)
                                      .buildSessionFactory()) {
      Session session = factory.getCurrentSession();

      // Department department = new Department("HR", 500, 1500);
      // Employee employee = new Employee("Oleg", "Ivanov", 800);
      // Employee employee2 = new Employee("Andrey", "Smirnov", 1000);
      // department.addEmployeeToDepartment(employee);
      // department.addEmployeeToDepartment(employee2);
      try {
        session.beginTransaction();

        // session.persist(department);
        Department department = session.find(Department.class, 2);

        // Employee dEmployee = session.find(Employee.class, 2);

        System.out.println(department);
        System.out.println(department.getEmps());
        // System.out.println(dEmployee.getDepartment());

        session.remove(department);

        session.getTransaction().commit();
        System.out.println("Done!");
      } catch (Exception e) {
        if (session.getTransaction() != null) {
          session.getTransaction().rollback();
        }
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}