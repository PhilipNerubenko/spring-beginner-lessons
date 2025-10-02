package com.philipnerubenko.spring.springboot.springboot_rest.dao;

import com.philipnerubenko.spring.springboot.springboot_rest.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object (DAO) implementation for managing employee data using JPA.
 * Provides CRUD operations for Employee entities through EntityManager.
 * <p>
 * Note: Some Hibernate-specific code is commented out and replaced with standard JPA equivalents.
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
  /**
   * JPA EntityManager for interacting with the persistence context.
   * Automatically injected by Spring.
   */
  @Autowired private EntityManager entityManager;

  /**
   * Retrieves all employee records from the database.
   * <p>
   * Executes a JPQL query "from Employee" to fetch all employee entities.
   *
   * @return List of Employee objects representing all records in the database
   */
  @Override
  // @Transactional
  public List<Employee> getAllEmployees() {
    Query query = entityManager.createQuery("from Employee");
    List<Employee> allEmployees = query.getResultList();

    return allEmployees;
  }

  /**
   * Persists or updates an employee record in the database.
   * <p>
   * Uses EntityManager's merge operation to either insert a new record or update an existing one.
   * The returned managed entity's ID is assigned back to the original employee object.
   *
   * @param employee the Employee object to be saved or updated
   */
  @Override
  public void saveEmployee(Employee employee) {
    //    Employee managed = entityManager.unwrap(Session.class).merge(employee);
    //
    //    employee.setId(managed.getId());
    Employee managed = entityManager.merge(employee);

    employee.setId(managed.getId());
  }

  /**
   * Retrieves an employee record by its unique identifier (ID).
   * <p>
   * Uses EntityManager's find method to load the employee record with the specified ID.
   *
   * @param id the unique identifier of the Employee to retrieve
   * @return the Employee object with the specified ID, or null if not found
   */
  @Override
  public Employee getEmployee(int id) {
    //    Session session = entityManager.unwrap(Session.class);
    //
    //    Employee employee = session.find(Employee.class, id);

    Employee employee = entityManager.find(Employee.class, id);

    return employee;
  }

  /**
   * Deletes an employee record from the database by its unique identifier (ID).
   * <p>
   * Executes a JPQL delete query with a named parameter to remove the record.
   *
   * @param id the unique identifier of the Employee to delete
   */
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
