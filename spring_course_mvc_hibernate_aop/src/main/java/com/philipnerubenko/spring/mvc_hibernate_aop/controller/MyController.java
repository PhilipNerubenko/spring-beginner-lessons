package com.philipnerubenko.spring.mvc_hibernate_aop.controller;

import com.philipnerubenko.spring.mvc_hibernate_aop.entity.Employee;
import com.philipnerubenko.spring.mvc_hibernate_aop.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A Spring MVC controller for managing employee data operations.
 * Handles HTTP requests related to viewing, creating, updating, and deleting employees.
 */
@Controller
public class MyController {
  /**
   * Employee service component for business logic operations.
   * Automatically injected by Spring.
   */
  @Autowired private EmployeeService employeeService;

  /**
   * Handles requests to the root URL ("/").
   * Retrieves all employees from the service and adds them to the model.
   * Returns the view name for displaying the employee list.
   *
   * @param model the Spring Model object for adding attributes to the view
   * @return the name of the view template to render ("all-employees")
   */
  @RequestMapping("/")
  public String showAllEmployees(Model model) {
    List<Employee> allEmployees = employeeService.getAllEmployees();

    model.addAttribute("allEmps", allEmployees);

    return "all-employees";
  }

  /**
   * Handles requests to "/addNewEmployee".
   * Initializes a new Employee object and adds it to the model.
   * Returns the view name for displaying the employee form.
   *
   * @param model the Spring Model object for adding attributes to the view
   * @return the name of the view template to render ("employee-info")
   */
  @RequestMapping("/addNewEmployee")
  public String addNewEmployee(Model model) {
    Employee employee = new Employee();

    model.addAttribute("employee", employee);

    return "employee-info";
  }

  /**
   * Handles requests to "/saveEmployee".
   * Saves the submitted Employee object using the service.
   * Redirects to the main employee list page.
   *
   * @param employee the Employee object bound from form data
   * @return redirect URL to the employee list
   */
  @RequestMapping("/saveEmployee")
  public String saveEmployee(@ModelAttribute("employee") Employee employee) {
    employeeService.saveEmployee(employee);
    return "redirect:/";
  }

  /**
   * Handles requests to "/updateInfo".
   * Retrieves an existing Employee by ID and adds it to the model.
   * Returns the view name for displaying the update form.
   *
   * @param id the ID of the employee to retrieve
   * @param model the Spring Model object for adding attributes to the view
   * @return the name of the view template to render ("employee-info")
   */
  @RequestMapping("/updateInfo")
  public String updateEmployee(@RequestParam("empId") int id, Model model) {
    Employee employee = employeeService.getEmployee(id);

    model.addAttribute("employee", employee);

    return "employee-info";
  }

  /**
   * Handles requests to "/deleteEmployee".
   * Deletes an Employee by ID using the service.
   * Redirects to the main employee list page.
   *
   * @param id the ID of the employee to delete
   * @return redirect URL to the employee list
   */
  @RequestMapping("/deleteEmployee")
  public String deleteEmployee(@RequestParam("empId") int id) {
    employeeService.deleteEmployee(id);
    return "redirect:/";
  }
}
