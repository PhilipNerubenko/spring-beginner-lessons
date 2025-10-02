package com.philipnerubenko.spring.springboot.spring_data_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Spring Data JPA project.
 * <p>
 * The {@literal @}SpringBootApplication annotation enables auto-configuration,
 * component scanning, and configuration properties for the Spring Boot application.
 */
@SpringBootApplication
public class SpringDataJpaApplication {

    /**
     * Entry point of the Spring Boot application.
     * <p>
     * Uses {@link SpringApplication#run(Class, String[])} to launch the application
     * with default settings and command-line arguments.
     *
     * @param args command line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

}

