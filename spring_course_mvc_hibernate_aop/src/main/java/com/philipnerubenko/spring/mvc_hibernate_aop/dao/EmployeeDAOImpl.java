package com.philipnerubenko.spring.mvc_hibernate_aop.dao;

import com.philipnerubenko.spring.mvc_hibernate_aop.entity.Employee;
import jakarta.transaction.Transactional;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the EmployeeDAO interface using Hibernate for database operations.
 * This class provides CRUD functionality for managing Employee entities through Hibernate sessions.
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
  /**
   * SessionFactory used to obtain Hibernate sessions for database interactions.
   * Automatically injected by Spring.
   */
  @Autowired private SessionFactory sessionFactory;

  /**
   * Retrieves all Employee records from the database.
   * <p>
   * Executes an HQL query ("from Employee") to fetch all employee entities.
   *
   * @return a List of Employee objects representing all records in the database
   */
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

  /**
   * Persists or updates an Employee entity in the database.
   * <p>
   * Uses Hibernate's merge operation to either insert a new record or update an existing one.
   *
   * @param employee the Employee object to be saved or updated
   */
  @Override
  public void saveEmployee(Employee employee) {
    Session session = sessionFactory.getCurrentSession();
    session.merge(employee);
  }

  /**
   * Retrieves an Employee entity by its primary key (ID).
   * <p>
   * Uses Hibernate's find method to load the employee record with the specified ID.
   *
   * @param id the unique identifier of the Employee to retrieve
   * @return the Employee object with the specified ID, or null if not found
   */
  @Override
  public Employee getEmployee(int id) {
    Session session = sessionFactory.getCurrentSession();
    Employee employee = session.find(Employee.class, id);
    return employee;
  }

  /**
   * Deletes an Employee entity from the database by its primary key (ID).
   * <p>
   * Executes an HQL delete query with a named parameter to remove the record.
   *
   * @param id the unique identifier of the Employee to delete
   */
  @Override
  public void deleteEmployee(int id) {
    Session session = sessionFactory.getCurrentSession();
    session.createMutationQuery("delete from Employee e where e.id = :id")
        .setParameter("id", id)
        .executeUpdate();
  }
}
