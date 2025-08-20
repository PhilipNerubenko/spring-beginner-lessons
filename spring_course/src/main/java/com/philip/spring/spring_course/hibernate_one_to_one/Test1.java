package com.philip.spring.spring_course.hibernate_one_to_one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.philip.spring.spring_course.hibernate_one_to_one.entity.Detail;
import com.philip.spring.spring_course.hibernate_one_to_one.entity.Employee;

public class Test1 {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory()) {
            
            Session session = factory.getCurrentSession();
            // Employee employee = new Employee("Oleg", "Smirnov", "Sales", 50000);
            // Detail detail = new Detail("Moscow", "987654321", "olegsmirnov@gmail.com");
            // employee.setEmpDetail(detail);

            try {
                session.beginTransaction();

                // session.persist(employee);
                Employee emp = session.find(Employee.class, 2);
                System.out.println(emp.getEmpDetail());
                session.remove(emp);

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