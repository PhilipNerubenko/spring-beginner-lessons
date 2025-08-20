package com.philip.spring.spring_course.hibernate_test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.philip.spring.spring_course.hibernate_test.entity.Employee;

public class Test3 {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory()) {
            
            Session session = factory.getCurrentSession();

            try {
                session.beginTransaction();

                List<Employee> employees = session.createQuery("from Employee", Employee.class)
                                            .getResultList();

                for (Employee e : employees) {
                    System.out.println(e);
                }

                System.out.println();

                List<Employee> employeesNameAlexander = session.createQuery("from Employee " + 
                "where firstName = 'Alexander' AND salary > 65000", Employee.class)
                                            .getResultList();

                for (Employee e : employeesNameAlexander) {
                    System.out.println(e);
                }

                session.getTransaction().commit();
                
                System.out.println("Employee gets");
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