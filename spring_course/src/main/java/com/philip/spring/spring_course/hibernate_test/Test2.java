package com.philip.spring.spring_course.hibernate_test;

import com.philip.spring.spring_course.hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2 {
  public static void main(String[] args) {
    try (SessionFactory factory = new Configuration()
                                      .configure("hibernate.cfg.xml")
                                      .addAnnotatedClass(Employee.class)
                                      .buildSessionFactory()) {
      Session session = factory.getCurrentSession();
      Employee employee = new Employee("Oleg", "Sidorov", "HR", 70000);

      try {
        session.beginTransaction();
        session.persist(employee);
        // session.getTransaction().commit();
        System.out.println("Employee saved");

        int myID = employee.getId();
        // session = factory.getCurrentSession();
        // session.beginTransaction();
        Employee employee2 = session.find(Employee.class, myID);
        session.getTransaction().commit();
        System.out.println("Get employee from MySQl: " + employee2);
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