package com.philip.spring.spring_course.hibernate_one_to_many_bi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.philip.spring.spring_course.hibernate_one_to_many_bi.entity.Department;
import com.philip.spring.spring_course.hibernate_one_to_many_bi.entity.Employee;

public class Test1 {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory()) {
            
            Session session = factory.getCurrentSession();

            // Department department = new Department("IT", 300, 1200);
            // Employee employee = new Employee("Philip", "Nerubenko", 800);
            // Employee employee2 = new Employee("Elena", "Smirnova", 1000);
            // department.addEmployeeToDepartment(employee);
            // department.addEmployeeToDepartment(employee2);
            try {
                session.beginTransaction();

                // session.persist(department);
                // Department department = session.find(Department.class, 1);

                Employee dEmployee = session.find(Employee.class, 2);

                // System.out.println(department);
                // System.out.println(department.getEmps());
                // System.out.println(dEmployee.getDepartment());

                session.remove(dEmployee);

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