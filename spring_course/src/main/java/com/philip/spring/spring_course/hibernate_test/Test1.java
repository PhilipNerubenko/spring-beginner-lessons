package com.philip.spring.spring_course.hibernate_test;

import com.philip.spring.spring_course.hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1 {
  public static void main(String[] args) {
    try (SessionFactory factory = new Configuration()
                                      .configure("hibernate.cfg.xml")
                                      .addAnnotatedClass(Employee.class)
                                      .buildSessionFactory()) {
      Session session = factory.getCurrentSession();
      Employee employee = new Employee("Alexander", "Ivanov", "IT", 60000);

      try {
        session.beginTransaction();
        session.persist(employee);
        session.getTransaction().commit();
        System.out.println("Employee saved");
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