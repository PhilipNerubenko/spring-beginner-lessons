package com.philipnerubenko.spring.security.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Spring configuration class for setting up web application context and defining beans.
 * Enables Spring MVC features, scans the "com.philipnerubenko.spring.security" package for
 * components, and configures essential beans like ViewResolver and DataSource.
 */
@Configuration
@ComponentScan("com.philipnerubenko.spring.security")
@EnableWebMvc
public class MyConfig {
  /**
   * Configures a ViewResolver to map logical view names to JSP files.
   * <p>
   * Sets the prefix as "/WEB-INF/view/" and suffix as ".jsp", allowing Spring to resolve views
   * by combining these values with controller-specified view names.
   *
   * @return configured InternalResourceViewResolver instance
   */
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
    internalResourceViewResolver.setPrefix("/WEB-INF/view/");
    internalResourceViewResolver.setSuffix(".jsp");
    return internalResourceViewResolver;
  }

  /**
   * Configures a ComboPooledDataSource for connecting to a MySQL database.
   * <p>
   * Sets up:
   * <ul>
   *   <li>JDBC driver class</li>
   *   <li>Database URL with connection parameters (SSL, timezone, encoding)</li>
   *   <li>Authentication credentials</li>
   * </ul>
   * Logs errors if configuration fails.
   *
   * @return configured ComboPooledDataSource instance
   * @throws PropertyVetoException if connection properties are invalid
   */
  @Bean
  public DataSource dataSource() {
    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    try {
      dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
      dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/"
          + "my_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&"
          + "useUnicode=true&characterEncoding=UTF-8");
      dataSource.setUser("bestuser");
      dataSource.setPassword("bestuser");
    } catch (PropertyVetoException ex) {
      System.getLogger(MyConfig.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
    return dataSource;
  }
}
