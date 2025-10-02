package com.philipnerubenko.spring.mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A Spring MVC controller class for handling employee-related HTTP requests.
 * This controller manages operations such as displaying employee forms,
 * processing input data, and validating employee information.
 */
@Controller
@RequestMapping("/employee")
public class MyController {
  /**
   * Handles requests to the root "/employee/" endpoint.
   * Returns the name of the first view template ("first-view").
   *
   * @return the name of the view template to render
   */
  @RequestMapping("/")
  public String showFirstView() {
    return "first-view";
  }

  /**
   * Handles requests to "/employee/askDetails".
   * Initializes a new Employee object and adds it to the model.
   * Returns the view template for collecting employee details.
   *
   * @param model the Spring Model object for adding attributes to the view
   * @return the name of the view template to render
   */
  @RequestMapping("/askDetails")
  public String askEmployeeDetails(Model model) {
    model.addAttribute("employee", new Employee());
    return "ask-emp-details-view";
  }

  //    @RequestMapping("/showDetails")
  //    public String showEmployeeDetails() {
  //    return "show-emp-details-view";
  //    }

  //    @RequestMapping("/showDetails")
  //    public String showEmployeeDetails(HttpServletRequest request, Model model) {
  //    String empName = request.getParameter("employeeName");
  //    empName = "Mr. " + empName;
  //
  //    model.addAttribute("nameAttribute", empName);
  //    return "show-emp-details-view";
  //    }

  //    @RequestMapping("/showDetails")
  //    public String showEmployeeDetails(@RequestParam("employeeName) String empName, Model model)
  //    { empName = "Mr. " + empName + "!";
  //
  //    model.addAttribute("nameAttribute", empName);
  //    return "show-emp-details-view";
  //    }

  /**
   * Handles requests to "/employee/showDetails".
   * Processes the submitted Employee object with validation.
   * If validation fails, returns the input form view again.
   * Otherwise, returns the details confirmation view.
   *
   * @param emp the Employee object bound from form data
   * @param bindingResult holds validation error information
   * @return the name of the view template to render
   */
  @RequestMapping("/showDetails")
  public String showEmployeeDetails(
      @Valid @ModelAttribute("employee") Employee emp, BindingResult bindingResult) {
    System.out.println("surname length = " + emp.getSurname().length());

    if (bindingResult.hasErrors()) {
      return "ask-emp-details-view";
    }

    return "show-emp-details-view";
  }
}
