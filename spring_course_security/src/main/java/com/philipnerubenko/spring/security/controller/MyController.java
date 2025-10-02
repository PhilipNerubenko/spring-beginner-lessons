package com.philipnerubenko.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring MVC controller that handles HTTP GET requests for employee-related views.
 * Provides access to different pages based on user roles:
 * - All employees can access the general view
 * - HR users can access HR-specific information
 * - Managers can access manager-specific information
 */
@Controller
public class MyController {
  /**
   * Handles GET requests to the root endpoint ("/").
   * Returns the view name for displaying general employee information accessible to all users.
   *
   * @return the name of the view template ("view_for_all_employees")
   */
  @GetMapping("/")
  public String getInfoForAllEmps() {
    return "view_for_all_employees";
  }

  /**
   * Handles GET requests to "/hr_info".
   * Returns the view name for displaying HR-specific information.
   * This endpoint is restricted to users with HR role permissions.
   *
   * @return the name of the view template ("view_for_hr")
   */
  @GetMapping("/hr_info")
  public String getInfoOnlyForHR() {
    return "view_for_hr";
  }

  /**
   * Handles GET requests to "/manager_info".
   * Returns the view name for displaying manager-specific information.
   * This endpoint is restricted to users with Manager role permissions.
   *
   * @return the name of the view template ("view_for_managers")
   */
  @GetMapping("/manager_info")
  public String getInfoOnlyForManagers() {
    return "view_for_managers";
  }
}
