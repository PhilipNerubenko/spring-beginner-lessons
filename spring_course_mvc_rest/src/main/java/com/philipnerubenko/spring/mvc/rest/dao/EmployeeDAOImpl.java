package com.philipnerubenko.spring.mvc.rest.dao;

import com.philipnerubenko.spring.mvc.rest.entity.Employee;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Repository implementation for managing employee data access operations.
 * Uses Hibernate SessionFactory to interact with the database and provides CRUD functionality
 * for Employee entities.
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
  /**
   * Hibernate SessionFactory used to obtain database sessions for executing queries.
   * Automatically injected by Spring.
   */
  @Autowired private SessionFactory sessionFactory;

  /**
   * Retrieves all employee records from the database.
   * <p>
   * Executes an HQL query "from Employee" to fetch all employee entities.
   *
   * @return List of Employee objects representing all records in the database
   */
  @Override
  public List<Employee> getAllEmployees() {
    Session session = sessionFactory.getCurrentSession();
    Query<Employee> query = session.createQuery("from Employee", Employee.class);
    List<Employee> allEmployees = query.getResultList();
    return allEmployees;
  }

  /**
   * Persists or updates an employee record in the database.
   * <p>
   * Uses Hibernate's merge operation to either insert a new record or update an existing one.
   * The returned managed entity's ID is assigned back to the original employee object.
   *
   * @param employee the Employee object to be saved or updated
   */
  @Override
  public void saveEmployee(Employee employee) {
    Employee managed = sessionFactory.getCurrentSession().merge(employee);
    employee.setId(managed.getId());
  }

  /**
   * Retrieves an employee record by its unique identifier (ID).
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
   * Deletes an employee record from the database by its unique identifier (ID).
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
