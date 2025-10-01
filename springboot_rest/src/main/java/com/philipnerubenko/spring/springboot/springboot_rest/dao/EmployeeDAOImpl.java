package com.philipnerubenko.spring.springboot.springboot_rest.dao;

import com.philipnerubenko.spring.springboot.springboot_rest.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
  @Autowired private EntityManager entityManager;

  @Override
  // @Transactional
  public List<Employee> getAllEmployees() {
//    Session session = entityManager.unwrap(Session.class);
//    
//    Query<Employee> query = session.createQuery("from Employee", Employee.class);
//    List<Employee> allEmployees = query.getResultList();

    Query query = entityManager.createQuery("from Employee");
    List<Employee> allEmployees = query.getResultList();
    
    return allEmployees;
  }

  @Override
public void saveEmployee(Employee employee) {
//    Employee managed = entityManager.unwrap(Session.class).merge(employee);
//    
//    employee.setId(managed.getId());   
    Employee managed = entityManager.merge(employee);
    
    employee.setId(managed.getId());  
}

  @Override
  public Employee getEmployee(int id) {
//    Session session = entityManager.unwrap(Session.class);
//
//    Employee employee = session.find(Employee.class, id);

    Employee employee = entityManager.find(Employee.class, id);

    return employee;
  }

  @Override
  public void deleteEmployee(int id) {
//    Session session = entityManager.unwrap(Session.class);
//
//    session.createMutationQuery("delete from Employee e where e.id = :id")
//        .setParameter("id", id)
//        .executeUpdate();

      Query query = entityManager.createQuery("delete from Employee e where e.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
  }
}
