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

@Configuration
@ComponentScan(basePackages = "com.philipnerubenko.spring.mvc.rest")
@EnableWebMvc
@EnableTransactionManagement
public class MyConfig {
    
    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8");
            dataSource.setUser("bestuser");
            dataSource.setPassword("bestuser");
        } catch (PropertyVetoException ex) {
            System.getLogger(MyConfig.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return dataSource;
    }
    
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
    
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new 
            HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        
        return transactionManager;
    }
}
