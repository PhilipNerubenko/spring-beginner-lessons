package com.philipnerubenko.spring.springboot.springboot_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main configuration class and entry point for the Spring Boot REST application.
 * <p>
 * The {@literal @}SpringBootApplication annotation combines:
 * - {@literal @}Configuration: enables configuration of the application using Java code
 * - {@literal @}EnableAutoConfiguration: enables Spring Boot's auto-configuration mechanism
 * - {@literal @}ComponentScan: scans for components, configurations, and services
 */
@SpringBootApplication
public class SpringbootRestApplication {

    /**
     * Entry point of the Spring Boot application.
     * <p>
     * Uses {@link SpringApplication#run(Class, String[])} to launch the application,
     * initializing the Spring context and starting the embedded web server.
     *
     * @param args command line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringbootRestApplication.class, args);
    }

}

