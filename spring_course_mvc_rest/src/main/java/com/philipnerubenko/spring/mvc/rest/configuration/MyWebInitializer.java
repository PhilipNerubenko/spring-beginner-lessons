package com.philipnerubenko.spring.mvc.rest.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Web application initializer class that configures Spring MVC using Java-based configuration.
 * Extends Spring's {@link AbstractAnnotationConfigDispatcherServletInitializer} to define
 * how the application should be initialized without XML configuration files.
 */
public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  /**
   * Returns an empty array of root configuration classes.
   * <p>
   * This implementation does not define any root-level configuration (e.g., security,
   * transaction management) outside the servlet context.
   *
   * @return an empty array of Class objects
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[0];
  }

  /**
   * Returns the configuration class for the DispatcherServlet.
   * <p>
   * This method specifies that the servlet should use the {@link MyConfig} class
   * for configuration, which includes database, Hibernate, and Spring MVC settings.
   *
   * @return an array containing the {@link MyConfig} class
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] {MyConfig.class};
  }

  /**
   * Defines the URL mapping for the DispatcherServlet.
   * <p>
   * Maps the servlet to handle all incoming HTTP requests starting with "/".
   *
   * @return an array with the string "/"" indicating the servlet mapping
   */
  @Override
  protected String[] getServletMappings() {
    return new String[] {"/"};
  }
}
