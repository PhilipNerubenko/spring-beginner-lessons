package com.philip.spring.spring_course.hibernate_test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.philip.spring.spring_course.hibernate_test.entity.Employee;

public class Test4 {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory()) {
            
            Session session = factory.getCurrentSession();

            try {
                session.beginTransaction();

                // Employee employee = session.find(Employee.class, 1);
                // employee.setSalary(15000);

                session.createMutationQuery("update Employee set salary = 100000 where firstName = 'Alexander'")
                    .executeUpdate();

                session.getTransaction().commit();
                
                System.out.println("Employee updated");
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