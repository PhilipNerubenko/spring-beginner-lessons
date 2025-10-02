package com.philip.spring.spring_course.hibernate_test;

import com.philip.spring.spring_course.hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test5 {
  public static void main(String[] args) {
    try (SessionFactory factory = new Configuration()
                                      .configure("hibernate.cfg.xml")
                                      .addAnnotatedClass(Employee.class)
                                      .buildSessionFactory()) {
      Session session = factory.getCurrentSession();

      try {
        session.beginTransaction();

        // Employee employee = session.find(Employee.class, 1);
        // session.remove(employee);

        session
            .createMutationQuery("delete Employee "
                + "where firstName = 'Alexander'")
            .executeUpdate();

        session.getTransaction().commit();

        System.out.println("Employee removed");
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