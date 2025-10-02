package com.philipnerubenko.spring.mvc.rest.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring configuration class that defines application-wide settings for:
 * <ul>
 *   <li>Database connection using ComboPooledDataSource</li>
 *   <li>Hibernate session factory configuration</li>
 *   <li>Transaction management setup</li>
 * </ul>
 *
 * Annotations:
 * <ul>
 *   <li>{@literal @}Configuration - Marks this class as a Spring configuration</li>
 *   <li>{@literal @}ComponentScan - Scans "com.philipnerubenko.spring.mvc.rest" for components</li>
 *   <li>{@literal @}EnableWebMvc - Enables Spring MVC features</li>
 *   <li>{@literal @}EnableTransactionManagement - Enables transaction management</li>
 * </ul>
 */
@Configuration
@ComponentScan(basePackages = "com.philipnerubenko.spring.mvc.rest")
@EnableWebMvc
@EnableTransactionManagement
public class MyConfig {
  /**
   * Configures and returns a ComboPooledDataSource instance for MySQL database connection.
   * <p>
   * Sets up:
   * <ul>
   *   <li>MySQL JDBC driver</li>
   *   <li>Database URL with connection parameters</li>
   *   <li>Authentication credentials</li>
   * </ul>
   *
   * @return configured DataSource instance
   * @throws PropertyVetoException if connection configuration fails
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

  /**
   * Creates and configures a LocalSessionFactoryBean for Hibernate.
   * <p>
   * Settings include:
   * <ul>
   *   <li>Linking to the dataSource bean</li>
   *   <li>Scanning entity classes in "com.philipnerubenko.spring.mvc.rest.entity"</li>
   *   <li>Setting Hibernate properties:
   *       <ul>
   *         <li>MySQL dialect</li>
   *         <li>SQL logging enabled</li>
   *       </ul>
   *   </li>
   * </ul>
   *
   * @return configured LocalSessionFactoryBean
   */
  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan("com.philipnerubenko.spring.mvc.rest.entity");

    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    hibernateProperties.setProperty("hibernate.show_sql", "true");
    sessionFactory.setHibernateProperties(hibernateProperties);
    return sessionFactory;
  }

  /**
   * Configures HibernateTransactionManager for managing database transactions.
   * <p>
   * Binds to the sessionFactory bean to provide transaction capabilities.
   *
   * @return configured HibernateTransactionManager
   */
  @Bean
  public HibernateTransactionManager transactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());

    return transactionManager;
  }
}
