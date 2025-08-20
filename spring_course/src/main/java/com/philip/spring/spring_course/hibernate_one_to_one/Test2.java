package com.philip.spring.spring_course.hibernate_one_to_one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.philip.spring.spring_course.hibernate_one_to_one.entity.Detail;
import com.philip.spring.spring_course.hibernate_one_to_one.entity.Employee;

public class Test2 {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory()) {
            
            Session session = factory.getCurrentSession();
            Employee employee = new Employee("Misha", "Sidorov", "HR", 85000);
            Detail detail = new Detail("Belgorod", "987654322", "mishasidorov@gmail.com");
            employee.setEmpDetail(detail);
            detail.setEmployee(employee);

            try {
                session.beginTransaction();

                // session.persist(employee);
                Detail detail2 = session.find(Detail.class, 1);
                //Employee emp = session.find(Employee.class, 2);
                // System.out.println(emp.getEmpDetail());
                detail2.getEmployee().setEmpDetail(null);
                session.remove(detail2);
                //System.out.println(detail2.getEmployee());
                // session.remove(emp);

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