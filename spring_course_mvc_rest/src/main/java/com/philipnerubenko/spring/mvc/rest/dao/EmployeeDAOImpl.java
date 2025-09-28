package com.philipnerubenko.spring.mvc.rest.dao;

import com.philipnerubenko.spring.mvc.rest.entity.Employee;
import jakarta.transaction.Transactional;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
  @Autowired private SessionFactory sessionFactory;

  @Override
  // @Transactional
  public List<Employee> getAllEmployees() {
    Session session = sessionFactory.getCurrentSession();
    //        List<Employee> allEmployees = session.createQuery("from Employee"
    //                , Employee.class)
    //                .getResultList();
    Query<Employee> query = session.createQuery("from Employee", Employee.class);
    List<Employee> allEmployees = query.getResultList();
    return allEmployees;
  }

//  @Override
//  public void saveEmployee(Employee employee) {
//    Session session = sessionFactory.getCurrentSession();
//
//    session.merge(employee);
//  }
  @Override
public void saveEmployee(Employee employee) {
    Employee managed = sessionFactory.getCurrentSession().merge(employee);
    employee.setId(managed.getId());   
}

  @Override
  public Employee getEmployee(int id) {
    Session session = sessionFactory.getCurrentSession();

    Employee employee = session.find(Employee.class, id);

    return employee;
  }

  @Override
  public void deleteEmployee(int id) {
    Session session = sessionFactory.getCurrentSession();

    session.createMutationQuery("delete from Employee e where e.id = :id")
        .setParameter("id", id)
        .executeUpdate();
  }
}
